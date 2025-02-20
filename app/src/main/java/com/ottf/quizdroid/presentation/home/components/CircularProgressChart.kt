package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressChart(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    strokeWidth: Dp = 16.dp,
    progressColor: Color = Color.Blue,
    trackColor: Color = Color.LightGray,
) {
    require(progress in 0f..1f) { "Progress must be between 0 and 1" }

    Canvas(
        modifier = modifier.size(size),
    ) {
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)

        drawArc(
            color = trackColor,
            startAngle = 270f,
            sweepAngle = 360f,
            useCenter = false,
            style = stroke,
        )

        drawArc(
            color = progressColor,
            startAngle = 270f,
            sweepAngle = 360 * progress,
            useCenter = false,
            style = stroke,
        )
    }
}

@Preview
@Composable
private fun CircularProgressChartPreview() {
    CircularProgressChart(progress = 0.75f)
}
