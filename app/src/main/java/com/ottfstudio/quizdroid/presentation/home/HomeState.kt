package com.ottfstudio.quizdroid.presentation.home

data class HomeState(
    val today: String = "",
    val isLoading: Boolean = true,
    val totalSolvedCount: Int = 0,
    val consecutiveSolvedCount: Int = 0,
    val correctRatePercent: Int = 0,
)
