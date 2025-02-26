package com.ottf.quizdroid.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state = _state
            .onStart {
                fetchToday()
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

        private fun fetchToday() {
            try {
                val currentDate = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
                val formattedDate = currentDate.format(formatter)
                _state.value = _state.value.copy(today = formattedDate)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching today", e)
                _state.value = _state.value.copy(today = "년 월 일")
            }
        }
    }
