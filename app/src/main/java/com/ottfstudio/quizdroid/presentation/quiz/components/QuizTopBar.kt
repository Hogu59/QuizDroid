@file:OptIn(ExperimentalMaterial3Api::class)

package com.ottfstudio.quizdroid.presentation.quiz.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
        ),
        modifier = Modifier.shadow(1.dp),
    )
}

@Preview
@Composable
private fun QuizTopBarPreview() {
    QuizTopBar(onNavigate = {})
}
