package com.ottfstudio.quizdroid.data.local.converter

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ottfstudio.quizdroid.domain.model.Quiz
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class QuizConverterTest {
    private lateinit var quizConverter: QuizConverter
    private lateinit var sampleQuiz: Quiz

    @Before
    fun setup() {
        quizConverter = QuizConverter(Gson())
        // Quiz.SAMPLE_QUIZ 사용하거나 테스트용 객체 생성
        sampleQuiz = Quiz(
            id = 1L,
            category = "테스트 카테고리",
            title = "테스트 제목",
            writer = "테스터",
            level = "초급",
            question = "테스트 질문입니다",
            options = listOf("옵션1", "옵션2", "옵션3"),
            answer = 2,
            description = listOf("설명1", "설명2"),
        )
    }

    @Test
    fun `fromQuiz converts Quiz to JSON string correctly`() {
        val jsonString = quizConverter.fromQuiz(sampleQuiz)

        // JSON에 모든 필드가 포함되었는지 확인
        val jsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
        assertEquals(1L, jsonObject.get("id").asLong)
        assert(jsonString.contains("\"id\":1"))
        assertEquals("테스트 카테고리", jsonObject.get("category").asString)
        assertEquals("테스트 질문입니다", jsonObject.get("question").asString)
        val options = jsonObject.getAsJsonArray("options")
        assertEquals("옵션1", options[0].asString)
        assertEquals("옵션2", options[1].asString)
        assertEquals("옵션3", options[2].asString)
        assertEquals(2, jsonObject.get("answer").asInt)
    }

    @Test
    fun `toQuiz converts JSON string to Quiz object correctly`() {
        // Quiz를 JSON으로 변환 후 다시 Quiz로 변환
        val jsonString = quizConverter.fromQuiz(sampleQuiz)
        val resultQuiz = quizConverter.toQuiz(jsonString)

        // 모든 필드가 정확히 복원되었는지 확인
        assertEquals(sampleQuiz.id, resultQuiz.id)
        assertEquals(sampleQuiz.category, resultQuiz.category)
        assertEquals(sampleQuiz.title, resultQuiz.title)
        assertEquals(sampleQuiz.writer, resultQuiz.writer)
        assertEquals(sampleQuiz.level, resultQuiz.level)
        assertEquals(sampleQuiz.question, resultQuiz.question)
        assertEquals(sampleQuiz.options, resultQuiz.options)
        assertEquals(sampleQuiz.answer, resultQuiz.answer)
        assertEquals(sampleQuiz.description, resultQuiz.description)
    }

    @Test
    fun `conversion handles Quiz_EMPTY correctly`() {
        val emptyQuiz = Quiz.EMPTY
        val jsonString = quizConverter.fromQuiz(emptyQuiz)
        val resultQuiz = quizConverter.toQuiz(jsonString)

        assertEquals(emptyQuiz.id, resultQuiz.id)
        assertEquals(emptyQuiz.options, resultQuiz.options)
        assertEquals(emptyQuiz.answer, resultQuiz.answer)
    }

    @Test
    fun testConversion() {
        val converter = QuizConverter(Gson())
        val originalQuiz = Quiz.SAMPLE_QUIZ

        // Quiz → JSON 변환
        val json = converter.fromQuiz(originalQuiz)

        // JSON → Quiz 변환
        val resultQuiz = converter.toQuiz(json)

        // 변환 전후가 동일한지 검증
        assertEquals(originalQuiz, resultQuiz)
    }
}
