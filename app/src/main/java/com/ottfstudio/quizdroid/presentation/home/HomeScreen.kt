package com.ottfstudio.quizdroid.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ottfstudio.quizdroid.presentation.home.components.QuestionCard
import com.ottfstudio.quizdroid.presentation.home.components.StudyStatisticsCard
import com.ottfstudio.quizdroid.presentation.home.components.TodayStatus
import com.ottfstudio.quizdroid.presentation.quiz.QuizState
import com.ottfstudio.quizdroid.presentation.quiz.QuizViewModel
import com.ottfstudio.quizdroid.ui.theme.QuizDroidTheme

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel,
    quizViewModel: QuizViewModel,
    onNavigateToQuiz: () -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val quizState by quizViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        quizState = quizState,
        onAction = { action ->
            when (action) {
                is HomeAction.OnNavigateToQuiz -> onNavigateToQuiz()
                is HomeAction.OnNavigateToSettings -> onNavigateToSettings()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    quizState: QuizState,
    onAction: (HomeAction) -> Unit,
) {
    Scaffold(
        // bottomBar = { BottomNavigationBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            // 오늘의 학습 섹션
            TodayStatus(
                date = quizState.today,
                isSolved = quizState.isSolved,
                onNavigateToSettings = { onAction(HomeAction.OnNavigateToSettings) },
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                    clip = false,
                ),
            )

            QuestionCard(
                quiz = quizState.quiz,
                onChallenge = { onAction(HomeAction.OnNavigateToQuiz) },
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        clip = false,
                    ),
            )

            StudyStatisticsCard(
                totalCount = state.totalSolvedCount,
                consecutiveCount = state.consecutiveSolvedCount,
                correctRate = state.correctRate,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        clip = false,
                    ),
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    QuizDroidTheme {
        HomeScreen(
            quizState = QuizState(today = "2025년 3월 10일"),
            state = HomeState(),
            onAction = {},
        )
    }
}
