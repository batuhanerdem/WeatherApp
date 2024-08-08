package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.domain.model.Result
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {

    override fun getWeatherByName(name: String): Flow<Resource<Result>> = flow {
        try {
            val resultList = service.getWeatherByCity(name)
            val singleResult = resultList.body()!!.result[0]
            emit(Resource.Success(singleResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }

}