package com.ottf.quizdroid.presentation.quiz

import com.ottf.quizdroid.domain.model.Quiz

data class QuizState(
    val isLoading: Boolean = true,
    val isShowAnswer: Boolean = false,
    val selectedOption: Int? = null,
    val isSolved: Boolean = false,
    val quiz: Quiz = Quiz.EMPTY,
)
