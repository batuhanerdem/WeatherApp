package com.example.weatherapp.domain.model.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Long,
//    @SerializedName("day")
//    val day: Day,
//    @SerializedName("hour")
//    val hour: List<Hour>
)
