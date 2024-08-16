package com.example.weatherapp.ui

import Screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.batuhanerdem.custom_navtype_generator.CustomNavTypeGenerator.serializeAnyType
import com.example.weatherapp.domain.model.weather.DayUi
import com.example.weatherapp.ui.forecast_screen.ForecastScreen
import com.example.weatherapp.ui.home_screen.HomeScreen
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
                typeOf<List<DayUi>>() to serializeAnyType<List<DayUi>>()
            )
        ) {
            val args = it.toRoute<Screens.ForecastScreen>()
            ForecastScreen(navController = navHostController, days = args.days)
        }

    }
}