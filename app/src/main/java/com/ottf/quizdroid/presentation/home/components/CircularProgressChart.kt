package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressChart(
    progress: Float, // 0.0 ~ 1.0 (진척도)
    modifier: Modifier = Modifier,
    size: Dp = 120.dp, // 원 크기
    strokeWidth: Dp = 16.dp, // 두께
    progressColor: Color = Color.Blue,
    trackColor: Color = Color.LightGray,
) {
    Canvas(
        modifier = modifier.size(size),
    ) {
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)

        // 배경 원형 트랙 (전체 원)
        drawArc(
            color = trackColor,
            startAngle = 270f,
            sweepAngle = 360f,
            useCenter = false,
            style = stroke,
        )

        // 진행된 부분 (진척도)
        drawArc(
            color = progressColor,
            startAngle = 270f, // 12시 방향 시작
            sweepAngle = 360 * progress, // 진척도 반영
            useCenter = false,
            style = stroke,
        )
    }
}
