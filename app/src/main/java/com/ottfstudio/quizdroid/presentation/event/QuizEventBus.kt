package com.ottfstudio.quizdroid.presentation.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object QuizEventBus {
    private val _events = MutableSharedFlow<QuizEvent>()
    val events = _events.asSharedFlow()

    suspend fun emitEvent(event: QuizEvent) {
        _events.emit(event)
    }
}

sealed class QuizEvent {
    data object QuizSolved : QuizEvent()
}
