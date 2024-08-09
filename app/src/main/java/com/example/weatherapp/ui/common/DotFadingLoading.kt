package com.example.weatherapp.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotFadingLoading(
    isLoading: Boolean, modifier: Modifier = Modifier, dotNumber: Int = 5, duration: Int = 800
) {
    if (!isLoading) return
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = duration * dotNumber, easing = LinearEasing)
        ), label = ""
    )
    val colorWhiteToBlack = infiniteTransition.animateColor(
        initialValue = Color.White,
        targetValue = Color.Black,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = duration, easing = LinearEasing),
        ),
        label = ""
    )
    val colorBlackToWhite = infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.White,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = duration, easing = LinearEasing
            )
        ),
        label = ""
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .progressSemantics(),
//            .size(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(dotNumber) { index ->
                val animatedIndex = (progress * dotNumber).toInt() % dotNumber
                val isCurrent = animatedIndex == index
                val isBefore = animatedIndex == index + 1

                val color = when {
                    isCurrent -> colorWhiteToBlack.value
                    isBefore -> colorBlackToWhite.value

                    else -> Color.White
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Black, shape = CircleShape)
                        .size(15.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}
