package com.ottf.quizdroid.data.model

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
        val EMPTY = QuizResponse(
            id = 0,
            category = "",
            title = "",
            writer = "",
            level = "",
            question = "오늘의 문제를 불러오는데 문제가 발생했습니다. 다시 시도해주세요.",
            answer = 0,
            description = emptyList(),
            createdAt = "",
            options = emptyList(),
        )
    }
}
