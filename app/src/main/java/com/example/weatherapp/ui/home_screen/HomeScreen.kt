package com.example.weatherapp.ui.home_screen

import Screens
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.weather.WeatherCurrent
import com.example.weatherapp.ui.common.DotFadingLoading
import com.example.weatherapp.ui.common.SnackBar
import com.example.weatherapp.ui.search_component.SearchComponent
import com.example.weatherapp.ui.theme.Background
import com.example.weatherapp.ui.theme.SearchBackground
import com.example.weatherapp.utils.ERROR
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

@SuppressLint("MissingPermission")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val locationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)

    val viewModel: HomeScreenViewModel = hiltViewModel()

    val loadingState = viewModel.dataClass.loadingState.collectAsStateWithLifecycle()
    val errorState = viewModel.dataClass.errorState.collectAsStateWithLifecycle()
    val cityNameState = viewModel.dataClass.cityName.collectAsStateWithLifecycle()
    val weatherState = viewModel.dataClass.weather.collectAsStateWithLifecycle()
    val forecastState = viewModel.dataClass.forecasts.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result[ACCESS_FINE_LOCATION] == true && result[ACCESS_COARSE_LOCATION] == true) {
                getLocation(navController, locationClient, viewModel)
            } else {
                //im not sure what to do
            }
        }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION
            )
        )
    }
    LaunchedEffect(key1 = cityNameState.value) {
        viewModel.getWeather()
    }
    LaunchedEffect(key1 = errorState.value) {
        if (errorState.value == "") return@LaunchedEffect
        snackBarHostState.showSnackbar(errorState.value)
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.TopCenter
    ) {

        Column(modifier = Modifier.padding(top = 100.dp)) {
            val currentWeatherState = weatherState.value ?: return@Column
            val currentCityState = cityNameState.value
            if (forecastState.value.isEmpty()) return@Column
            CityWeatherInfo(
                weather = currentWeatherState, cityName = currentCityState
            )
            WeatherDetails(weatherState = currentWeatherState)
            ButtonArea(buttonOnClick = {
                navController.navigate(
                    Screens.ForecastScreen(
                        forecastState.value
                    )
                )
            })

        }
        DotFadingLoading(
            isLoading = loadingState.value,
        )
        SearchComponent()
        SnackBar(
            snackBarHostState = snackBarHostState,
            text = errorState.value,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}

@Composable
fun CityWeatherInfo(
    modifier: Modifier = Modifier, weather: WeatherCurrent, cityName: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = cityName,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 18.dp))

        Text(
            text = "${weather.temperature.toInt()}°",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = weather.condition,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Today",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

    }
}

@Composable
fun WeatherDetails(modifier: Modifier = Modifier, weatherState: WeatherCurrent) {
    Column(modifier = Modifier) {
        WeatherInfoItem(
            resId = R.drawable.ic_wind,
            staticString = "Wind",
            dynamicString = "${weatherState.windSpeed}/kmh"
        )
        WeatherInfoItem(
            resId = R.drawable.ic_humidity,
            staticString = "Humidity",
            dynamicString = "${weatherState.humidity}%"
        )
        WeatherInfoItem(
            resId = R.drawable.ic_pressure,
            staticString = "Pressure",
            dynamicString = "${weatherState.pressure}in"
        )
        WeatherInfoItem(
            resId = R.drawable.ic_visibility,
            staticString = "Visibility",
            dynamicString = "${weatherState.visibility}mi"
        )
        WeatherInfoItem(
            resId = R.drawable.ic_uv_index,
            staticString = "Uv Index",
            dynamicString = "${weatherState.uvIndex} high"
        )
    }
}


@Composable
fun WeatherInfoItem(
    modifier: Modifier = Modifier, resId: Int, staticString: String, dynamicString: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .background(Background, RoundedCornerShape(15.dp))
        ) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = staticString,
                fontSize = 19.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = dynamicString, fontSize = 17.sp, color = SearchBackground)
        }
    }
}

@Composable
fun ButtonArea(modifier: Modifier = Modifier, buttonOnClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { buttonOnClick() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Background,
                contentColor = Color.Black,
                disabledContentColor = Background,
                disabledContainerColor = Color.Black
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "5-Day Forecast",
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(17f, TextUnitType.Sp),
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLocation(
    navController: NavController,
    locationClient: FusedLocationProviderClient,
    viewModel: HomeScreenViewModel
) {
    if (viewModel.dataClass.cityName.value.isNotEmpty()) return
    val coder = Geocoder(navController.context, Locale.getDefault())
    locationClient.lastLocation.addOnSuccessListener { location ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {// can get crash for some reason
                coder.getFromLocation(location.latitude, location.longitude, 1) {
                    val resultName = it[0].adminArea
                    viewModel.dataClass.cityName.value = resultName
                }
            } catch (e: Exception) {
                Log.e(ERROR, "getLocation: ${e.localizedMessage} ")
            }

        } else {
            try {
                val result = coder.getFromLocation(location.latitude, location.longitude, 1)
                if (result!!.isNotEmpty()) {
                    val resultName = result[0].adminArea
                    viewModel.dataClass.cityName.value = resultName
                } else {
                    Log.e(ERROR, "No geocoding results found")
                }
            } catch (e: IOException) {
                Log.e(ERROR, "Geocoding failed ${e.localizedMessage}")
            } catch (e: Exception) {
                Log.e(ERROR, "getLocation: ${e.localizedMessage}")
            }
        }
    }
}
