package com.example.weatherapp.ui.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository

) : ViewModel() {
    val dataClass = HomeScreenDataClass()

    init {
        dataClass.loadingState.value = dataClass.city.value == null
    }

    fun getWeather() {
        val cityName = dataClass.city.value?.name ?: return //handle error
        dataClass.loadingState.value = true
        viewModelScope.launch {
            weatherRepository.getWeatherByName(cityName).collect { it ->
                dataClass.loadingState.value = false
                it.data?.let {
                    Log.d(SUCCESS, "weat: $it")
                    dataClass.weather.value = it
                }
            }
        }

    }

}