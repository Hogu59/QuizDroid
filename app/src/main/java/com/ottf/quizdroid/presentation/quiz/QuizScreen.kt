package com.ottf.quizdroid.presentation.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ottf.quizdroid.domain.Quiz
import com.ottf.quizdroid.presentation.quiz.components.AnswerSheet
import com.ottf.quizdroid.presentation.quiz.components.QuizCard
import com.ottf.quizdroid.presentation.quiz.components.QuizTopBar
import com.ottf.quizdroid.presentation.quiz.components.SubmitButton

@Composable
fun QuizScreenRoot(
    viewModel: QuizViewModel,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is QuizAction.OnNavigateBack -> onNavigateBack()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
private fun QuizScreen(
    state: QuizState,
    onAction: (QuizAction) -> Unit,
) {
    val quiz = state.quiz
    println("is Selected : ${state.selectedOption}")

    Scaffold(
        topBar = {
            QuizTopBar(
                onNavigate = { onAction(QuizAction.OnNavigateBack) },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            // 문제 카드
            QuizCard(
                quiz = quiz,
                isLoading = state.isLoading,
                selectedOption = state.selectedOption,
                onOptionSelect = { onAction(QuizAction.OnSelectOption(it)) },
            )

            if (!state.isShowAnswer) {
                Spacer(modifier = Modifier.weight(1f))

                // 답안 제출 버튼
                SubmitButton(
                    enabled = state.selectedOption != null && !state.isLoading,
                    onClick = { onAction(QuizAction.OnSubmitAnswer) },
                )
            } else {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                )

                AnswerSheet(
                    answer = quiz.answer,
                    selectedOption = state.selectedOption ?: -1,
                    description = quiz.description,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    val quiz = Quiz.SAMPLE_QUIZ

    QuizScreen(
        state = QuizState(
            quiz = quiz,
            isLoading = false,
            selectedOption = null,
        ),
        onAction = {},
    )
}
