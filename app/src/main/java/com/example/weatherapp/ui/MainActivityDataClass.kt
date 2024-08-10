package com.example.weatherapp.ui

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.MutableStateFlow

data class MainActivityDataClass(
    val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val errorState: MutableStateFlow<String> = MutableStateFlow(""),
    var city: MutableStateFlow<City?> = MutableStateFlow(null),
    var weather: MutableStateFlow<Weather?> = MutableStateFlow(null)
)