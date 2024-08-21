package com.example.weatherapp.ui.search_component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.ui.common.SnackBar
import com.example.weatherapp.ui.home_screen.HomeScreenViewModel
import com.example.weatherapp.ui.search_component.city_item.CityItem
import com.example.weatherapp.ui.theme.Background
import com.example.weatherapp.ui.theme.SearchBackground

@Composable
fun SearchComponent(modifier: Modifier = Modifier) {
    val viewModel: SearchViewModel = hiltViewModel()
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val focusManager = LocalFocusManager.current

    val loadingState = viewModel.dataClass.loadingState.collectAsStateWithLifecycle()
    val errorState = viewModel.dataClass.errorState.collectAsStateWithLifecycle()
    val city = viewModel.dataClass.city.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }


    var textState by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(key1 = textState) {
        if (textState.text.isNotEmpty()) return@LaunchedEffect
        viewModel.setCityNull()
    }
    LaunchedEffect(key1 = errorState.value) {
        if (errorState.value.isEmpty()) return@LaunchedEffect
        snackBarHostState.showSnackbar(errorState.value)
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 10.dp)
            .background(
                Background, RoundedCornerShape(20.dp)
            )
    ) {

        Row(
            modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = SearchBackground,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .clickable { viewModel.getCity(textState.text) })

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
        city.value?.let {
            CityItem(city = it, modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    homeScreenViewModel.dataClass.cityName.value = it.name
                    viewModel.setCityNull()
                    textState = TextFieldValue("")
                    focusManager.clearFocus()
                })
        }
    }
    SnackBar(
        snackBarHostState = snackBarHostState,
        text = errorState.value,
    )

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchComponentPreview() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 25.dp)
            .background(
                Background, RoundedCornerShape(
                    20.dp
                )
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = SearchBackground,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

//            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = "",
                onValueChange = {
//                    textState = it
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
//            city.value?.let {
//                roundPair.value = Pair(0, 0)
//            }
        }
        CityItem(city = City("Sivas", "TR"))

    }
}
