package com.ottfstudio.quizdroid.presentation.home

import com.ottfstudio.quizdroid.domain.model.Quiz

data class HomeState(
    val isLoading: Boolean = true,
    val isSolved: Boolean = false,
    val today: String = "년 월 일",
    val quiz: Quiz? = null,
)
