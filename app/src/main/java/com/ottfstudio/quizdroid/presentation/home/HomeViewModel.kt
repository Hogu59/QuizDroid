package com.ottfstudio.quizdroid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state = _state
            .onStart {
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
    }
