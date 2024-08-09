package com.example.weatherapp.domain.model

data class Weather(
    val date: String,
    val day: String,
    val degree: String,
    val description: String,
    val humidity: String,
    val max: String,
    val min: String,
    val night: String,
    val status: String
)