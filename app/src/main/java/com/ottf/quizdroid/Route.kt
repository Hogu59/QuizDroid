package com.ottf.quizdroid

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object QuizDroidGraph : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Quiz : Route
}
