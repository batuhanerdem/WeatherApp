package com.example.weatherapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.SearchBackground

@Composable
fun SnackBar(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
) {
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .background(SearchBackground, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(),
    ) { snackbarData ->
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            text = snackbarData.visuals.message,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
    }

}