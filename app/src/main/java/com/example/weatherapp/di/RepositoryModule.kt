package com.example.weatherapp.di

import com.example.weatherapp.data.repository.CityNameRepositoryImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.service.CityNameService
import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.domain.repository.CityNameRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Named("CityName")
    @Provides
    fun provideCityNameRepository(service: CityNameService): CityNameRepository =
        CityNameRepositoryImpl(service)

    @Named("Weather")
    @Provides
    fun provideWeatherRepository(service: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(service)

}