package com.example.weatherapp.domain.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("location_name") val locationName: String,
    @SerializedName("temperature_celsius") val temperatureCelsius: Double,
    @SerializedName("temperature_fahrenheit") val temperatureFahrenheit: Double,
    @SerializedName("description") val description: String,
    @SerializedName("wind_speed_kmh") val windSpeedKmh: Double,
    @SerializedName("wind_speed_mph") val windSpeedMph: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("temperature_min_celsius") val temperatureMinCelsius: Double,
    @SerializedName("temperature_max_celsius") val temperatureMaxCelsius: Double,
    @SerializedName("temperature_min_fahrenheit") val temperatureMinFahrenheit: Double,
    @SerializedName("temperature_max_fahrenheit") val temperatureMaxFahrenheit: Double,
    @SerializedName("precipitation_mm") val precipitationMm: Double,
    @SerializedName("precipitation_inch") val precipitationInch: Double,
    @SerializedName("pressure_hpa") val pressureHpa: Int,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("cloudiness_percentage") val cloudinessPercentage: Int,
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String
)
