package com.ottf.quizdroid.presentation.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottf.quizdroid.R
import com.ottf.quizdroid.domain.Quiz

@Composable
fun QuizCard(
    quiz: Quiz,
    isLoading: Boolean,
    onOptionSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: Int? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 난이도 표시
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFEB3B), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                Text(
                    text = if (isLoading) "???" else quiz.level,
                    fontSize = 12.sp,
                    color = Color.Black,
                )
            }

            Text(
                text = if (isLoading) stringResource(R.string.loading_quiz) else quiz.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
            )

            Text(
                text = if (isLoading) "" else quiz.question,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 12.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (!isLoading) {
                quiz.let {
                    it.options.forEachIndexed { index, option ->
                        AnswerOption(
                            index = index,
                            text = option,
                            isSelected = selectedOption == index,
                            onClick = { onOptionSelect(index) },
                            modifier = Modifier.padding(top = 16.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizCardPreviewLoading() {
    QuizCard(
        quiz = Quiz.SAMPLE_QUIZ,
        isLoading = true,
        onOptionSelect = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizCardPreviewLoaded() {
    QuizCard(
        quiz = Quiz.SAMPLE_QUIZ,
        isLoading = false,
        onOptionSelect = {},
    )
}
