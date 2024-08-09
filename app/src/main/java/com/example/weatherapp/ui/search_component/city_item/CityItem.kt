package com.example.weatherapp.ui.search_component.city_item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.model.City

@Composable
fun CityItem(modifier: Modifier = Modifier, city: City) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Text(
            text = city.name,
            textAlign = TextAlign.Start,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = city.country,
            textAlign = TextAlign.Start,
            fontSize = TextUnit(17f, TextUnitType.Sp),
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.padding(15.dp))

    }

}