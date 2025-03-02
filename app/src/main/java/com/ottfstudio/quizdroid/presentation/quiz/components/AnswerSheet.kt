package com.ottfstudio.quizdroid.presentation.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ottfstudio.quizdroid.domain.model.Quiz
import com.ottfstudio.quizdroid.ui.theme.CustomTypography

@Composable
fun AnswerSheet(
    answer: Int,
    selectedOption: Int,
    description: List<String>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            AnswerResult(answer - 1 == selectedOption)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "정답: ${('A' + answer - 1)}",
                style = CustomTypography.titleSmall,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 해설 카드
            DescriptionCard(
                description = description,
            )
        }
    }
}

@Composable
private fun DescriptionCard(
    description: List<String>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "해설",
                style = CustomTypography.labelMedium,
            )

            description.forEach {
                Text(
                    text = it,
                    style = CustomTypography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnswerSheetPreviewWrong() {
    AnswerSheet(
        answer = 1,
        selectedOption = 2,
        description = Quiz.SAMPLE_QUIZ.description,
    )
}

@Preview
@Composable
private fun AnswerSheetPreviewCorrect() {
    AnswerSheet(
        answer = 1,
        selectedOption = 1,
        description = Quiz.SAMPLE_QUIZ.description,
    )
}
