package com.ottf.quizdroid.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottf.quizdroid.domain.Quiz
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
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
            // 예시: 1초 후에 퀴즈 데이터를 업데이트함
            delay(1000L)
            _state.value = _state.value.copy(
                quiz = Quiz.SAMPLE_QUIZ,
                isLoading = false,
                selectedOption = _state.value.selectedOption,
            )
        }
    }
}
