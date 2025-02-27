package com.ottfstudio.quizdroid.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottfstudio.quizdroid.domain.model.Record
import com.ottfstudio.quizdroid.domain.repository.QuizRepository
import com.ottfstudio.quizdroid.domain.repository.RecordRepository
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
        private val recordRepository: RecordRepository,
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
                    _state.value = _state.value.copy(isSolved = true)
                    solveQuiz()
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
                    val record = checkTodayRecord(today)

                    if (record == null) {
                        val fetchedQuiz = quizRepository.fetchTodayQuiz(today)
                        _state.value = _state.value.copy(
                            quiz = fetchedQuiz,
                            isLoading = false,
                            selectedOption = _state.value.selectedOption,
                        )
                    } else {
                        _state.value = _state.value.copy(
                            quiz = record.quiz,
                            isLoading = false,
                            selectedOption = record.selectedOption,
                            isSolved = true,
                            isShowAnswer = true,
                        )
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(isLoading = false)
                    println("Failed to fetch quiz: $e")
                }
            }
        }

        private suspend fun checkTodayRecord(date: String): Record? {
            return recordRepository.fetchQuizRecord(date)
        }

        private fun fetchToday(): String {
            return try {
                val currentDate = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
                val formattedDate = currentDate.format(formatter)
                require(formattedDate.isNotBlank()) { "Formatted date is blank" }
                _state.value = _state.value.copy(today = formattedDate)
                formattedDate
            } catch (e: Exception) {
                Log.e("QuizViewModel", "Error fetching today", e)
                throw IllegalStateException("Error fetching today", e)
            }
        }

        private fun solveQuiz() {
            viewModelScope.launch {
                try {
                    recordRepository.insertQuizRecord(
                        Record(
                            date = state.value.today,
                            quiz = state.value.quiz,
                            selectedOption = state.value.selectedOption ?: -1,
                            isCorrect = state.value.quiz.answer == state.value.selectedOption,
                            isSolved = state.value.isSolved,
                        ),
                    )
                } catch (e: Exception) {
                    println("Failed to solve quiz: $e")
                }
            }
        }
    }
