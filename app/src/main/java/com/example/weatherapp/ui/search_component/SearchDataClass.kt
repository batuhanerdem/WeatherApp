package com.example.weatherapp.ui.search_component

import com.example.weatherapp.domain.model.City
import kotlinx.coroutines.flow.MutableStateFlow

data class SearchDataClass(
    val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val errorState: MutableStateFlow<String> = MutableStateFlow(""),
    var city: MutableStateFlow<City?> = MutableStateFlow(null)
)