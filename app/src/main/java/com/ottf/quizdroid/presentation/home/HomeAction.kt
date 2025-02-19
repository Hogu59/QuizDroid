package com.ottf.quizdroid.presentation.home

sealed interface HomeAction {
    data object OnNavigateToQuiz : HomeAction

    data object OnNavigateToStatistics : HomeAction

    data object OnNavigateToSettings : HomeAction
}
