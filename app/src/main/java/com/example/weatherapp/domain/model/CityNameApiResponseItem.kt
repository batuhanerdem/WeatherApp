package com.example.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class CityNameApiResponseItem(
    val country: String,
    @SerializedName("is_capital") val isCapital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int
)