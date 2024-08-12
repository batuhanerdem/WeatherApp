package com.example.weatherapp.domain.model.weather

data class WeatherDto(
    val location: Location, val current: Current, val forecast: Forecast
)