package com.example.weatherapp.ui.forecast_screen

import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherapp.domain.model.weather.Forecast
import com.example.weatherapp.utils.SUCCESS
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier, navController: NavController, forecast: Forecast
) {
    LaunchedEffect(Unit) {
        repeat(forecast.forecastday.size) { index ->
            val dayName = getDayOfWeek(forecast.forecastday[index].date).lowercase()
                .replaceFirstChar { it.uppercase() }
            Log.d(
                SUCCESS, "ForecastScreen: $dayName"
            )
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


    }

}

private fun getDayOfWeek(dateString: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        date.dayOfWeek.name.lowercase(Locale.getDefault()).replaceFirstChar { it.uppercase() }
    } else {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse(dateString)
        if (date != null) {
            val calendar = Calendar.getInstance()
            calendar.time = date
            when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SUNDAY -> "Sunday"
                Calendar.MONDAY -> "Monday"
                Calendar.TUESDAY -> "Tuesday"
                Calendar.WEDNESDAY -> "Wednesday"
                Calendar.THURSDAY -> "Thursday"
                Calendar.FRIDAY -> "Friday"
                Calendar.SATURDAY -> "Saturday"
                else -> "Unknown"
            }
        } else {
            "Invalid date"
        }
    }
}