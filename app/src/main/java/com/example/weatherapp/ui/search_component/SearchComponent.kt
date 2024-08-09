package com.example.weatherapp.ui.search_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Background
import com.example.weatherapp.ui.theme.SearchBackground

@Composable
fun SearchComponent(modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Background, RoundedCornerShape(20.dp))
                .padding(horizontal = 25.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = SearchBackground
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = textState,
                onValueChange = {
                    textState = it
                },
                maxLines = 1,
                placeholder = {
                    Text(
                        text = "Search for a location",
                        color = SearchBackground,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
            )
        }
        if (textState.text.isNotEmpty()) Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.Yellow)
        ) {

        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchComponentPreview() {
    SearchComponent()

}