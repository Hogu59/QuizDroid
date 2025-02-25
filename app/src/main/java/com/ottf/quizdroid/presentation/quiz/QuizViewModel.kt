package com.ottf.quizdroid.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottf.quizdroid.domain.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel
    @Inject
    constructor() : ViewModel() {
        private var _state = MutableStateFlow(QuizState())
        val state = _state.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _state.value,
        )

        init {
            fetchQuiz()
        }

        fun onAction(action: QuizAction) {
            when (action) {
                is QuizAction.OnSubmitAnswer -> {
                    _state.value = _state.value.copy(isShowAnswer = true)
                }

                is QuizAction.OnSelectOption -> {
                    _state.value = _state.value.copy(selectedOption = action.option)
                }

                is QuizAction.OnNavigateBack -> {
                }
            }
        }

        private fun fetchQuiz() {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isLoading = true)
                    // TODO: Replace with actual API call
                    delay(1000L)
                    val quiz = Quiz.SAMPLE_QUIZ
                    _state.value = _state.value.copy(
                        quiz = quiz,
                        isLoading = false,
                        selectedOption = _state.value.selectedOption,
                    )
                } catch (e: Exception) {
                    _state.value = _state.value.copy(isLoading = false)
                    println("Failed to fetch quiz: $e")
                }
            }
        }
    }
