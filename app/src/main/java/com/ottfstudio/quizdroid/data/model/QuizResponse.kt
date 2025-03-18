package com.ottfstudio.quizdroid.data.model

data class QuizResponse(
    val id: Long,
    val category: String,
    val title: String,
    val writer: String,
    val level: String,
    val question: String,
    val answer: Int,
    val description: List<String>,
    val createdAt: String,
    val options: List<String>,
) {
    companion object {
        private const val ERROR_LOADING_QUIZ = "오늘의 문제를 불러오는데 문제가 발생했습니다. 다시 시도해주세요."

        val EMPTY = QuizResponse(
            id = 0,
            category = "",
            title = "",
            writer = "",
            level = "",
            question = ERROR_LOADING_QUIZ,
            answer = 0,
            description = emptyList(),
            createdAt = "",
            options = emptyList(),
        )
    }
}
