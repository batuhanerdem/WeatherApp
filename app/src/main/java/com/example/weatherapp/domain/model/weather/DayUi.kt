package com.example.weatherapp.domain.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class DayUi(
    val dayName: String,
    val lowTemp: String,
    val highTemp: String,
    val icon: String,
    val condition: String
)
