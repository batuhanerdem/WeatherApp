package com.example.weatherapp.data.service


interface WeatherService {

    fun getWeatherByCity()

    private object ServiceConstants {
        const val CONTENT_TYPE = "content-type: application/json"

        const val AUTHORIZATION = "authorization: ${ApiKeys.WEATHER}}"
        const val ENDPOINT_GET_NEWS = "news/getNews"
        const val QUERY_COUNTRY = "country"
        const val QUERY_TAG = "tag"
    }
}