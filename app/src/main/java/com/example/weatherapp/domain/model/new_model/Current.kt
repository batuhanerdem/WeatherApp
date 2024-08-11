package com.example.weatherapp.domain.model.new_model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("cloud") val cloud: Int,

    @SerializedName("condition") val condition: Condition,

    @SerializedName("dewpoint_c") val dewpointC: Double,

    @SerializedName("feelslike_c") val feelslikeC: Double,

    @SerializedName("gust_kph") val gustKph: Double,

    @SerializedName("heatindex_c") val heatindexC: Double,

    @SerializedName("humidity") val humidity: Int,

    @SerializedName("is_day") val isDay: Int,

    @SerializedName("last_updated") val lastUpdated: String,

    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Int,

    @SerializedName("precip_in") val precipIn: Double,

    @SerializedName("precip_mm") val precipMm: Double,

    @SerializedName("pressure_in") val pressureIn: Double,

    @SerializedName("pressure_mb") val pressureMb: Double,

    @SerializedName("temp_c") val tempC: Double,

    @SerializedName("uv") val uv: Double,

    @SerializedName("vis_km") val visKm: Double,

    @SerializedName("wind_degree") val windDegree: Int,

    @SerializedName("wind_dir") val windDir: String,

    @SerializedName("wind_kph") val windKph: Double,

    @SerializedName("windchill_c") val windchillC: Double,

)
