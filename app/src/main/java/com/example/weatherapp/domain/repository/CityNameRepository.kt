package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CityNameRepository {
    fun getCityByName(name: String): Flow<Resource<City>>
}