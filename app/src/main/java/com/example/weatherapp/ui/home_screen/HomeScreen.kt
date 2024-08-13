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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.domain.model.weather.WeatherCurrent
import com.example.weatherapp.ui.common.DotFadingLoading
import com.example.weatherapp.ui.common.SnackBar
import com.example.weatherapp.ui.search_component.SearchComponent
import com.example.weatherapp.utils.ERROR
import com.example.weatherapp.utils.SUCCESS
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
            CityWeatherInfo(
                weather = currentWeatherState, cityName = currentCityState
            )

            Button(
                onClick = { navController.navigate(Screens.ForecastScreen(viewModel.dataClass.forecast.value!!)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {

            }

            Text(
                text = "5-day forecast",
                fontWeight = FontWeight.SemiBold,
                fontSize = 21.sp,
                color = Color.Black
            )
            //set search component height as float
            //put a spacer at the top of the column

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
            text = cityName,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 18.dp))

        Text(
            text = "${weather.temperature}°",
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
        Spacer(modifier = Modifier.padding(vertical = 25.dp))

        Text(
            text = "H:${weather.weatherDescription}° L:${weather.feelsLike}°",
            fontWeight = FontWeight.Normal,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = Color.Black
        )

    }
}

@SuppressLint("MissingPermission")
private fun getLocation(
    navController: NavController,
    locationClient: FusedLocationProviderClient,
    viewModel: HomeScreenViewModel
) {
    val coder = Geocoder(navController.context, Locale.getDefault())
    locationClient.lastLocation.addOnSuccessListener { location ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d(SUCCESS, "testing: $location ")
            coder.getFromLocation(location.latitude, location.longitude, 1) {
                val resultName = it[0].adminArea
                viewModel.dataClass.cityName.value = resultName
            }
        } else {
            try {
                val result = coder.getFromLocation(location.latitude, location.longitude, 1)
                if (result!!.isNotEmpty()) {
                    val resultName = result[0].adminArea
                    viewModel.dataClass.cityName.value = resultName
                } else {
                    Log.d(ERROR, "No geocoding results found")
                }
            } catch (e: IOException) {
                Log.e(ERROR, "Geocoding failed ${e.localizedMessage}")
            } catch (e: Exception) {
                Log.d(ERROR, "getLocation: ${e.localizedMessage}")
            }
        }
    }
}
