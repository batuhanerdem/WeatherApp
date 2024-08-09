package com.example.weatherapp.utils

import kotlinx.coroutines.flow.MutableStateFlow

fun MutableStateFlow<String>.clear() {
    this.value = ""
}