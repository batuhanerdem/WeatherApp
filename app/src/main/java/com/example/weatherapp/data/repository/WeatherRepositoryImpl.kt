package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.domain.model.weather.Current
import com.example.weatherapp.domain.model.weather.WeatherCurrent
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

    override fun getWeatherByName(name: String): Flow<Resource<WeatherCurrent>> = flow {
        try {
            val resultList = service.getWeatherByCity(name)
            Log.d(ERROR, "getWeatherByName: $resultList ")
            val weatherCurrent = resultList.body()!!.current.toWeatherCurrent()

            emit(Resource.Success(weatherCurrent))
        } catch (e: Exception) {
            emit(Resource.Error("getWeatherByName${e.localizedMessage}"))
        }
    }



    private fun Current.toWeatherCurrent(): WeatherCurrent {
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