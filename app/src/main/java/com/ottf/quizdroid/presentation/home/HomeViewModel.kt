package com.ottf.quizdroid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel : ViewModel() {
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
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
        val formattedDate = currentDate.format(formatter)
        _state.value = _state.value.copy(today = formattedDate)
    }
}
