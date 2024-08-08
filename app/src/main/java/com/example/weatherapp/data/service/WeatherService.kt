package com.example.weatherapp.data.service

import com.example.weatherapp.domain.model.WeatherApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface WeatherService {
    @Headers(Constants.CONTENT_TYPE, Constants.AUTHORIZATION)
    @GET(Constants.ENDPOINT_GET_WEATHER)
    suspend fun getWeatherByCity(
        @Query(Constants.QUERY_DATA_CITY) cityName: String,
        @Query(Constants.QUERY_DATA_LANG) languageCode: String = "tr",
    ): Response<WeatherApiResponse>

    private object Constants {
        const val CONTENT_TYPE = "content-type: application/json"
        const val AUTHORIZATION = "authorization: ${ApiKeys.WEATHER}"
        const val ENDPOINT_GET_WEATHER = "weather/getWeather"
        const val QUERY_DATA_LANG = "data.lang"
        const val QUERY_DATA_CITY = "data.city"
    }
}