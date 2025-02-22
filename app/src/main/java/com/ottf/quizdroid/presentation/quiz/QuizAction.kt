package com.ottf.quizdroid.presentation.quiz

sealed interface QuizAction {
    data object OnSubmitAnswer : QuizAction

    data object OnNavigateBack : QuizAction

    data class OnSelectOption(val option: Int) : QuizAction
}
