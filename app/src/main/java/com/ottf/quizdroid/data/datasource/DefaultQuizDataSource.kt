package com.ottf.quizdroid.data.datasource

import com.ottf.quizdroid.data.api.QuizService
import com.ottf.quizdroid.data.model.QuizResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultQuizDataSource
    @Inject
    constructor(
        private val quizService: QuizService,
    ) : QuizDataSource {
        override suspend fun fetchTodayQuizzes(): List<QuizResponse> {
            val response = quizService.getQuizzes()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Failed to fetch quizzes ${response.code()}")
            }
        }

        override suspend fun fetchTodayQuiz(date: String): List<QuizResponse> {
            val response = quizService.getTodayQuiz(date = "eq.$date")
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Failed to fetch quiz ${response.code()}")
            }
        }
    }
