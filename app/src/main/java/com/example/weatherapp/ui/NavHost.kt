package com.example.weatherapp.ui

import Screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.weatherapp.domain.model.weather.Forecast
import com.example.weatherapp.ui.forecast_screen.ForecastScreen
import com.example.weatherapp.ui.home_screen.HomeScreen
import com.example.weatherapp.utils.CustomNavType
import kotlin.reflect.typeOf

@Composable
fun NavigationHost(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navHostController, startDestination = Screens.Home, modifier = modifier
    ) {

        composable<Screens.Home> {
            HomeScreen(
                navController = navHostController,
            )
        }
        composable<Screens.ForecastScreen>(
            typeMap = mapOf(
                typeOf<Forecast>() to CustomNavType.serializeAnyType<Forecast>()
            )
        ) {
            val args = it.toRoute<Screens.ForecastScreen>()
            ForecastScreen(navController = navHostController, forecast = args.days)
        }

    }
}