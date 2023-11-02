package com.ahuaman.lavayaexpress.presentation.screens.custom

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateLinearProgressIndicator(
    modifier: Modifier = Modifier,
    height: Dp = 20.dp,
    progressColor: Color = Color.Red,
    backgroundColor: Color = Color.LightGray,
    speed: Float = 2f
) {
    val transition = rememberInfiniteTransition(label = "")
    val progressOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (1000 / speed).toInt(),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = modifier.height(height = height)) {
        val cornerSize = CornerRadius(height.toPx())
        val barWidth = size.width * 0.6f
        val barHeight = size.height * 0.2f
        val startX = size.width * progressOffset
        val endX = startX + barWidth

        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(0f, (size.height - barHeight) / 2),
            size = Size(size.width, barHeight),
            cornerRadius = cornerSize
        )

        if(endX < size.width) {
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(startX, (size.height - barHeight) / 2),
                size = Size(barWidth, barHeight),
                cornerRadius = cornerSize
            )
        } else {
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(startX, (size.height - barHeight) / 2),
                size = Size(size.width - startX, barHeight),
                cornerRadius = cornerSize
            )
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(0f, (size.height - barHeight) / 2),
                size = Size(endX - size.width, barHeight),
                cornerRadius = cornerSize
            )
        }

    }
}

@Preview
@Composable
fun IndeterminateLinearProgressIndicatorPrev() {
    IndeterminateLinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        height = 20.dp,
        progressColor = Color.Red,
        backgroundColor = Color.LightGray
    )
}