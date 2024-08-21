package com.example.weatherapp.ui.forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.domain.model.weather.DayUi
import com.example.weatherapp.ui.theme.SearchBackground

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier, days: List<DayUi>
) {

    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.padding(top = 100.dp))
        Text(text = "3-day Forecast", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        DayList(days = days)
    }
}

@Composable
fun DayList(days: List<DayUi>) {
    repeat(days.size) { index ->
        DayItem(day = days[index])
    }
}

@Composable
fun DayItem(modifier: Modifier = Modifier, day: DayUi) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp, max = 90.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = day.icon,
            contentDescription = "",
            modifier = Modifier
                .height(56.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = day.dayName,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Row() {
                Text(
                    text = "${day.condition}, High: ${day.highTemp}, Low: ${day.lowTemp}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = SearchBackground,
                    overflow = TextOverflow.Visible
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForecastScreenPreview() {
    val sampleDays = listOf(
        DayUi(
            dayName = "Monday",
            lowTemp = "18°C",
            highTemp = "25°C",
            icon = "",
            condition = "Sunny"
        ), DayUi(
            dayName = "Tuesday",
            lowTemp = "16°C",
            highTemp = "23°C",
            icon = "",
            condition = "Cloudy"
        ), DayUi(
            dayName = "Wednesday",
            lowTemp = "17°C",
            highTemp = "24°C",
            icon = "",
            condition = "Rainy"
        )
    )

    ForecastScreen(
        days = sampleDays
    )
}

