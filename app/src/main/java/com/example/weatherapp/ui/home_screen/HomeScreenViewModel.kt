package com.example.weatherapp.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.mapper.WeatherMapper.toDayUi
import com.example.weatherapp.domain.mapper.WeatherMapper.toWeatherCurrent
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository

) : ViewModel() {
    val dataClass = HomeScreenDataClass()


    init {
        dataClass.loadingState.value = dataClass.cityName.value.isEmpty()
    }

    fun getWeather() {
        val cityName = dataClass.cityName.value
        if (cityName.isEmpty()) return
        dataClass.loadingState.value = true
        viewModelScope.launch {
            weatherRepository.getWeatherByName(cityName).collect { it ->
                dataClass.loadingState.value = false

                it.message?.let {
                    dataClass.errorState.value = it
                }

                it.data?.let {
                    dataClass.weather.value = it.current.toWeatherCurrent()
                    dataClass.forecasts.value = it.forecast.toDayUi()
                }
            }
        }
    }
}