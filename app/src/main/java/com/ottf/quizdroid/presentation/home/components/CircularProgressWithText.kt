package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressWithText(
    progress: Float, // 0.0 ~ 1.0
    total: Int = 5, // 전체 문제 개수
    current: Int = 3, // 현재 완료한 개수
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(120.dp),
    ) {
        CircularProgressChart(progress = progress)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "$current/$total", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "문제", fontSize = 14.sp, color = Color.Gray)
        }
    }
}
