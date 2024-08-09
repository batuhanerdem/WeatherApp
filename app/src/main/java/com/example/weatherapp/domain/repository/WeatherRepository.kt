package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Result
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherByName(name: String): Flow<Resource<Weather>>
}