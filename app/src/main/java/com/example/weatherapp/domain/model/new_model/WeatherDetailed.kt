package com.example.weatherapp.domain.model.new_model

data class WeatherDetailed(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)