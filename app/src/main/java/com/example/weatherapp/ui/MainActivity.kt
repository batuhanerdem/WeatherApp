package com.example.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.ui.common.DotFadingLoading
import com.example.weatherapp.ui.search_component.SearchComponent
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val viewModel: MainActivityViewModel = hiltViewModel()

                val loadingState = viewModel.dataClass.loadingState.collectAsStateWithLifecycle()
                val errorState = viewModel.dataClass.errorState.collectAsStateWithLifecycle()
                val cityState = viewModel.dataClass.city.collectAsStateWithLifecycle()
                val weatherState = viewModel.dataClass.weather.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = cityState.value) {
                    viewModel.getWeather()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding),
                        contentAlignment = Alignment.TopCenter
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

            }
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


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//            SearchComponent()
                SearchComponent()

                Box(
                    modifier = Modifier, contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "testing", fontWeight = FontWeight.Bold, fontSize = TextUnit(
                            20f, TextUnitType.Sp
                        )
                    )

                }

            }
        }
    }
}