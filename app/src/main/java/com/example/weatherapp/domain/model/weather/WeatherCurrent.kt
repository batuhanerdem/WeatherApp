package com.example.weatherapp.domain.model.weather

data class WeatherCurrent(
    val condition: String,
    val temperature: Double, // in °C
    val feelsLike: Double, // in °C
    val weatherDescription: String, val windSpeed: Double, // in km/h
    val humidity: Int, // in percentage
    val pressure: Double, // in mb
    val visibility: Double, // in km
    val uvIndex: Double, val gustSpeed: Double // in km/h
)