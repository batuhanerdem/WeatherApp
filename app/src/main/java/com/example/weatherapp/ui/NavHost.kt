package com.example.weatherapp.ui

import Screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.home_screen.HomeScreen

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
//        composable<Screens.Forecast> {
//            val args = it.toRoute<Screens.Forecast>()
//            //(days = args.days)
//        }

    }
}