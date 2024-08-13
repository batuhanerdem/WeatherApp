package com.example.weatherapp.domain.mapper

import com.example.weatherapp.domain.model.weather.Current
import com.example.weatherapp.domain.model.weather.WeatherCurrent

object WeatherMapper {
    fun Current.toWeatherCurrent(): WeatherCurrent {
        return WeatherCurrent(
            condition = this.condition.text,
            temperature = tempC,
            feelsLike = feelslikeC,
            weatherDescription = condition.text,
            windSpeed = windKph,
            humidity = humidity,
            pressure = pressureMb,
            visibility = visKm,
            uvIndex = uv,
            gustSpeed = gustKph
        )
    }
}