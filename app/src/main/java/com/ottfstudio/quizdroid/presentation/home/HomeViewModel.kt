package com.ottfstudio.quizdroid.presentation.home

import android.util.Log
import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottfstudio.quizdroid.domain.repository.RecordRepository
import com.ottfstudio.quizdroid.presentation.event.QuizEvent
import com.ottfstudio.quizdroid.presentation.event.QuizEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val recordRepository: RecordRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state = _state
            .onStart {
                fetchTotalSolvedCount()
                fetchConsecutiveCount()
            }.onEach {
                QuizEventBus.events.onEach { event ->
                    when (event) {
                        is QuizEvent.QuizSolved -> {
                            viewModelScope.launch {
                                fetchTotalSolvedCount()
                                fetchConsecutiveCount()
                            }
                        }
                    }
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                _state.value,
            )

        fun onAction(action: HomeAction) {
            when (action) {
                is HomeAction.OnNavigateToQuiz -> {
                }

                is HomeAction.OnNavigateToStatistics -> {
                }

                is HomeAction.OnNavigateToSettings -> {
                }
            }
        }

        private suspend fun fetchConsecutiveCount() {
            val today = fetchToday()
            val consecutiveCount = recordRepository.fetchLatestConsecutiveSolvedCount(today)

            _state.update {
                it.copy(consecutiveSolvedCount = consecutiveCount)
            }
        }

        private suspend fun fetchTotalSolvedCount() {
            val count = recordRepository.fetchTotalSolvedCount()
            _state.update {
                it.copy(totalSolvedCount = count)
            }
            fetchCorrectRate(count)
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
                Log.e("HomeViewModel", "Error fetching today", e)
                throw IllegalStateException("Error fetching today", e)
            }
        }

        private suspend fun fetchCorrectRate(totalSolvedCount: Int) {
            if (totalSolvedCount == 0) return
            val correctCount = recordRepository.fetchTotalCorrectCount()

            _state.update { it.copy(correctRate = (correctCount.toFloat() / totalSolvedCount.toFloat() * 100).toInt()) }
        }
    }
