package com.example.weatherapp.ui.home_screen

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeScreenDataClass(
    val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val errorState: MutableStateFlow<String> = MutableStateFlow(""),
    var city: MutableStateFlow<City?> = MutableStateFlow(null),
    var weather: MutableStateFlow<Weather?> = MutableStateFlow(null)
)