package com.example.weatherapp.data.service

import com.example.weatherapp.domain.model.CityNameApiResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.example.weatherapp.data.ApiKeys

interface CityNameService {
    @Headers(Constants.X_API_KEY)
    @GET(Constants.ENDPOINT_V1_CITY)
    suspend fun getCityByName(@Query(Constants.QUERY_NAME) cityName: String): Response<List<CityNameApiResponseItem>>

    private object Constants {
        const val X_API_KEY = "X-Api-Key: ${ApiKeys.CITY_NAME}"
        const val ENDPOINT_V1_CITY = "city"
        const val QUERY_NAME = "name"

    }
}