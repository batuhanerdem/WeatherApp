package com.example.weatherapp.data.repository

import com.example.weatherapp.data.service.CityNameService
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.CityNameApiResponseItem
import com.example.weatherapp.domain.repository.CityNameRepository
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CityNameRepositoryImpl @Inject constructor(private val service: CityNameService) :
    CityNameRepository {
    override fun getCityByName(name: String): Flow<Resource<City>> = flow {
        try {
            val list = service.getCityByName(name)
            val city = list.body()!![0].toCity()
            emit(Resource.Success(city))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }

    }

    private fun CityNameApiResponseItem.toCity(): City {
        return City(this.name)
    }

}