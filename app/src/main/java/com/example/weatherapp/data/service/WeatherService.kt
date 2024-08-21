package com.example.weatherapp.data.service

import com.example.weatherapp.data.ApiKeys
import com.example.weatherapp.domain.model.weather.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET(Constants.ENDPOINT_FORECAST)
    suspend fun getWeatherByCity(
        @Query(Constants.QUERY_CITY) cityName: String,
        @Query(Constants.QUERY_DAYS) days: Int = 3,
        @Query(Constants.QUERY_API_KEY) apiKey: String = ApiKeys.WEATHER
    ): Response<WeatherDto>

    private object Constants {
        const val ENDPOINT_FORECAST = "forecast.json"
        const val QUERY_CITY = "q"
        const val QUERY_DAYS = "days"
        const val QUERY_API_KEY = "key"
    }
}