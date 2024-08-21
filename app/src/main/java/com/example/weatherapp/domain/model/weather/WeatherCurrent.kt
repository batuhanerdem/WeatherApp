package com.example.weatherapp.domain.model.weather

data class WeatherCurrent(
    val condition: String,
    val temperature: Double,
    val feelsLike: Double,
    val weatherDescription: String,
    val windSpeed: Double,
    val humidity: Int,
    val pressure: Double,
    val visibility: Double,
    val uvIndex: Double,
    val gustSpeed: Double
)