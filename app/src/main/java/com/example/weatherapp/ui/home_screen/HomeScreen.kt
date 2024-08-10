package com.example.weatherapp.ui.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.ui.common.DotFadingLoading
import com.example.weatherapp.ui.search_component.SearchComponent

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel: HomeScreenViewModel = hiltViewModel()

    val loadingState = viewModel.dataClass.loadingState.collectAsStateWithLifecycle()
    val errorState = viewModel.dataClass.errorState.collectAsStateWithLifecycle()
    val cityState = viewModel.dataClass.city.collectAsStateWithLifecycle()
    val weatherState = viewModel.dataClass.weather.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = cityState.value) {
        viewModel.getWeather()
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.TopCenter
    ) {

        Column(modifier = Modifier.padding(top = 100.dp)) {
            val currentWeatherState = weatherState.value ?: return@Column
            val currentCityState = cityState.value ?: return@Column
            CityWeatherInfo(
                weather = currentWeatherState, city = currentCityState
            )

            Text(
                text = "5-day forecast",
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(21f, TextUnitType.Sp),
                color = Color.Black
            )

        }
        DotFadingLoading(
            isLoading = loadingState.value,
        )
        SearchComponent()


    }
}

@Composable
fun CityWeatherInfo(modifier: Modifier = Modifier, weather: Weather, city: City) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Weather",
            fontWeight = FontWeight.SemiBold,
            fontSize = TextUnit(21f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = city.name,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 18.dp))

        Text(
            text = "${weather.degree}°",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = weather.status,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 25.dp))

        Text(
            text = "H:${weather.max}° L:${weather.min}°",
            fontWeight = FontWeight.Normal,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = Color.Black
        )

    }
}
