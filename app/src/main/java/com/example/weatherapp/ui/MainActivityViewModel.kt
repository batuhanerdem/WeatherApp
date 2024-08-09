package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.di.CityNameApi
import com.example.weatherapp.di.WeatherApi
import com.example.weatherapp.domain.repository.CityNameRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @WeatherApi private val weatherRepository: WeatherRepository,
    @CityNameApi private val cityNameRepository: CityNameRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            weatherRepository.getWeatherByName("sivas").collect {
                it.data?.let {
                    Log.d(SUCCESS, "weat: ${it}")
                }
            }
            cityNameRepository.getCityByName("sivas").collect {
//                Log.d(SUCCESS, "city: $it ")
//                it.data?.let {
//                    Log.d(SUCCESS, ": $it")
//                }
            }
        }
    }
}