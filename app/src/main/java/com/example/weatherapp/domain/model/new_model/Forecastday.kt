package com.example.weatherapp.domain.model.new_model

import com.google.gson.annotations.SerializedName

data class Forecastday(
    val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    val day: Day,
)