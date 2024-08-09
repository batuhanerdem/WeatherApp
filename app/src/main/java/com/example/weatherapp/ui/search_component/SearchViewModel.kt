package com.example.weatherapp.ui.search_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.repository.CityNameRepository
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.utils.clear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val cityNameRepository: CityNameRepository) :
    ViewModel() {
    val dataClass = SearchDataClass()

    fun getCity(name: String) {
        dataClass.loadingState.value = true
        cityNameRepository.getCityByName(name).onEach { resource: Resource<City> ->
            dataClass.loadingState.value = false
            resource.message?.let {
                dataClass.errorState.value = it
                return@onEach
            }
            resource.data?.let {
                dataClass.errorState.clear()
                dataClass.city.value = it
                return@onEach
            }

        }.launchIn(viewModelScope)
    }

    fun setCityNull() {
        dataClass.city.value = null
    }
}