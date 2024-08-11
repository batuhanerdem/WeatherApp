package com.example.weatherapp.domain.model.new_model

import com.google.gson.annotations.SerializedName


data class Day(
    val avghumidity: Int,
    @SerializedName("avgtemp_c") val avgtempC: Double,
    @SerializedName("avgvis_km") val avgvisKm: Double,
    val condition: Condition,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerializedName("maxtemp_c") val maxtempC: Double,
    @SerializedName("maxwind_kph") val maxwindKph: Double,
    @SerializedName("mintemp_c") val mintempC: Double,
    val uv: Double
)