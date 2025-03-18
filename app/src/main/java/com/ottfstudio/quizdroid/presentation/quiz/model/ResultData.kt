package com.ottfstudio.quizdroid.presentation.quiz.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class ResultData(
    val text: String,
    val textColor: Color,
    val icon: Int,
    val iconSize: Dp,
    val boxColor: Color,
)
