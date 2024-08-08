package com.example.weatherapp.di

import com.example.weatherapp.data.service.CityNameService
import com.example.weatherapp.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideWeatherService(@Named("Weather") retrofitWeatherApi: Retrofit): WeatherService {
        return retrofitWeatherApi.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideCityNameService(@Named("CityName") retrofitCityNameApi: Retrofit): CityNameService {
        return retrofitCityNameApi.create(CityNameService::class.java)
    }
}