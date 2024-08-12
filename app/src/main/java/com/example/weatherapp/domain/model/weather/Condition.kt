package com.example.weatherapp.domain.model.weather

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
)
