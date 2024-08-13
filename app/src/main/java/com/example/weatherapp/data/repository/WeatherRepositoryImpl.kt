package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.domain.model.weather.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.ERROR
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {

    override fun getWeatherByName(name: String): Flow<Resource<WeatherDto>> = flow {
        try {
            val resultList = service.getWeatherByCity(name)
            Log.d(ERROR, "getWeatherByName: $resultList ")
            emit(Resource.Success( resultList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error("getWeatherByName${e.localizedMessage}"))
        }
    }


}