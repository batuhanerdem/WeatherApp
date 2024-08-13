package com.example.weatherapp.domain.model.weather

import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    val forecastday: List<Forecastday>
)