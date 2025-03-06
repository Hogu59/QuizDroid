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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
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
                updateTodayState()
                fetchTotalSolvedCount()
                fetchConsecutiveCount()
            }.onEach {
                QuizEventBus.events
                    .onEach { event ->
                        when (event) {
                            is QuizEvent.QuizSolved -> {
                                viewModelScope.launch {
                                    fetchTotalSolvedCount()
                                    fetchConsecutiveCount()
                                }
                            }
                        }
                    }
                    .launchIn(viewModelScope)
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
            val today = getFormattedToday()
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

        private fun getFormattedToday(): String {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

            return try {
                currentDate.format(formatter)
            } catch (e: DateTimeParseException) {
                Log.e(TAG, "Error formatting date", e)
                "Error: Could not fetch date"
            } catch (e: Exception) {
                Log.e(TAG, "Unexpected error fetching today", e)
                "Error: Unexpected error"
                // TODO 예외 처리 제외 및 스낵바 노출로 변경
            }
        }

        private fun updateTodayState() {
            val formattedDate = getFormattedToday()
            _state.value = _state.value.copy(today = formattedDate)
        }

        private suspend fun fetchCorrectRate(totalSolvedCount: Int) {
            if (totalSolvedCount == 0) return
            val correctCount = recordRepository.fetchTotalCorrectCount()

            _state.update { it.copy(correctRate = (correctCount.toFloat() / totalSolvedCount.toFloat() * 100).toInt()) }
        }

        companion object {
            private const val TAG = "HomeViewModel"
        }
    }
