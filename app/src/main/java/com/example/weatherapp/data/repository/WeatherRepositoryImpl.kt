package com.example.weatherapp.data.repository

import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.domain.model.Result
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherRepository {

    override fun getWeatherByName(name: String): Flow<Resource<Weather>> = flow {
        try {
            val resultList = service.getWeatherByCity(name)
            val singleResult = resultList.body()!!.result[0]
            val weather = singleResult.toWeather()
            emit(Resource.Success(weather))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }

    private fun Result.toWeather(): Weather {
        return Weather(
            date = this.date,
            day = this.day,
            degree = this.degree,
            description = this.description,
            humidity = this.humidity,
            max = this.max,
            min = this.min,
            night = this.night,
            status = this.status
        )
    }
}