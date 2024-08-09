package com.example.weatherapp.di

import javax.inject.Named
import javax.inject.Qualifier

@Named("Weather")
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherApi

@Named("CityName")
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CityNameApi