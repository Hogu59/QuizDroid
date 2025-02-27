package com.ottfstudio.quizdroid.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottfstudio.quizdroid.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class QuizViewModel
    @Inject
    constructor(
        private val quizRepository: QuizRepository,
    ) : ViewModel() {
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
                    val today = fetchToday()
                    val fetchedQuiz = quizRepository.fetchTodayQuiz(today)
                    _state.value = _state.value.copy(
                        quiz = fetchedQuiz,
                        isLoading = false,
                        selectedOption = _state.value.selectedOption,
                    )
                } catch (e: Exception) {
                    _state.value = _state.value.copy(isLoading = false)
                    println("Failed to fetch quiz: $e")
                }
            }
        }

        private fun fetchToday(): String {
            return try {
                val currentDate = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
                val formattedDate = currentDate.format(formatter)
                require(formattedDate.isNotBlank()) { "Formatted date is blank" }
                formattedDate
            } catch (e: Exception) {
                Log.e("QuizViewModel", "Error fetching today", e)
                throw IllegalStateException("Error fetching today", e)
            }
        }
    }
