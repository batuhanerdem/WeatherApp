package com.example.weatherapp.utils

import android.util.Log

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message) {
        override fun toString(): String {
            return "Error message : $message"
        }

        init {
            Log.d(ERROR, this.toString())
        }
    }
}