package com.ottfstudio.quizdroid.presentation.quiz

import com.ottfstudio.quizdroid.domain.model.Quiz

data class QuizState(
    val isLoading: Boolean = true,
    val isShowAnswer: Boolean = false,
    val today: String = "년 월 일",
    val selectedOption: Int? = null,
    val isSolved: Boolean = false,
    val quiz: Quiz = Quiz.EMPTY,
)
