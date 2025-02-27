package com.ottfstudio.quizdroid.domain.model

data class Quiz(
    val id: Long,
    val category: String,
    val title: String,
    val writer: String,
    val level: String,
    val question: String,
    val options: List<String>,
    val answer: Int,
    val description: List<String>,
) {
    init {
        require(answer == -1 || (answer in 1..options.size)) {
            "Answer index must be -1 or within options range (1..${options.size})"
        }
    }

    companion object {
        val EMPTY = Quiz(
            id = -1L,
            category = "",
            title = "문제를 불러오는 중입니다.",
            writer = "",
            level = "???",
            question = "",
            options = emptyList(),
            answer = -1,
            description = listOf(""),
        )

        val SAMPLE_QUIZ = Quiz(
            id = 1,
            category = "코루틴",
            title = "코루틴 스코프와 컨텍스트",
            writer = "admin",
            level = "중급",
            question = "코루틴 스코프와 컨텍스트의 차이점을 설명하고, 각각의 사용 사례를 제시하시오.",
            options = listOf(
                "코루틴 스코프는 실행 범위를 정의하고, 컨텍스트는 실행 환경을 제공한다.",
                "스코프는 취소 처리를, 컨텍스트는 디스패처를 관리한다.",
                "스코프와 컨텍스트는 동일한 개념으로 혼용된다.",
                "둘 다 코루틴의 생명주기만을 관리한다.",
            ),
            answer = -1,
            description = listOf(
                "코루틴 스코프(CoroutineScope)와 코루틴 컨텍스트(CoroutineContext)는 서로 다른 역할을 합니다.",
                "1. 코루틴 스코프:\n코루틴의 실행 범위를 정의\n코루틴의 생명주기 관리\n취소 및 예외 처리 담당\n",
                "2. 코루틴 컨텍스트:\n코루틴의 실행 환경을 제공\n디스패처, 예외 처리기, 취소 처리기 등을 포함\n",
            ),
        )
    }
}
