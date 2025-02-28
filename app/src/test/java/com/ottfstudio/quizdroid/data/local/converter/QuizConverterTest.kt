package com.ottfstudio.quizdroid.data.local.converter

import com.google.gson.Gson
import com.ottfstudio.quizdroid.domain.model.Quiz
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class QuizConverterTest {
    @Test
    fun testConversion() {
        val converter = QuizConverter(Gson())
        val originalQuiz = Quiz.SAMPLE_QUIZ

        // Quiz → JSON 변환
        val json = converter.fromQuiz(originalQuiz)

        // JSON → Quiz 변환
        val resultQuiz = converter.toQuiz(json)

        // 변환 전후가 동일한지 검증
        assertThat(resultQuiz).isEqualTo(originalQuiz)
    }
}
