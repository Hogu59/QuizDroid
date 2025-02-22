@file:OptIn(ExperimentalMaterial3Api::class)

package com.ottf.quizdroid.presentation.quiz.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun QuizTopBar(
    onNavigate: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "문제 풀기",
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigate() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
    )
}

@Preview
@Composable
private fun QuizTopBarPreview() {
    QuizTopBar(onNavigate = {})
}
