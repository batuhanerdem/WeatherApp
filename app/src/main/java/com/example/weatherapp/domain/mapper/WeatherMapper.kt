package com.example.weatherapp.domain.mapper

import android.os.Build
import com.example.weatherapp.domain.model.weather.Current
import com.example.weatherapp.domain.model.weather.DayUi
import com.example.weatherapp.domain.model.weather.Forecast
import com.example.weatherapp.domain.model.weather.Forecastday
import com.example.weatherapp.domain.model.weather.WeatherCurrent
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

object WeatherMapper {
    fun Current.toWeatherCurrent(): WeatherCurrent {
        return WeatherCurrent(
            condition = this.condition.text,
            temperature = tempC,
            feelsLike = feelslikeC,
            weatherDescription = condition.text,
            windSpeed = windKph,
            humidity = humidity,
            pressure = pressureMb,
            visibility = visKm,
            uvIndex = uv,
            gustSpeed = gustKph
        )
    }


    fun Forecast.toDayUi(): List<DayUi> {
        return forecastday.map { forecastday ->
            forecastday.toDayUi()
        }
    }

    private fun Forecastday.toDayUi(): DayUi {
        val dayName = getDayOfWeek(date)
        val lowTemp = "${day.mintempC}°C"
        val highTemp = "${day.maxtempC}°C"
        val icon = day.condition.icon.addHttpsPrefix() // Assuming icon URL
        val condition = day.condition.text

        return DayUi(
            dayName = dayName,
            lowTemp = lowTemp,
            highTemp = highTemp,
            icon = icon,
            condition = condition
        )
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

    private fun String.addHttpsPrefix(): String {
        return if (this.startsWith("//")) {
            "https:${this}" // Prepend "https:" to the URL.
        } else if (!this.startsWith("http://") && !this.startsWith("https://")) {
            "https://$this" // Ensure the URL starts with "https://" if it's missing.
        } else {
            this // If it already starts with "http://" or "https://", return as is.
        }
    }
}