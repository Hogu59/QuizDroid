package com.ottfstudio.quizdroid.data.remote

import com.ottfstudio.quizdroid.data.api.QuizService
import com.ottfstudio.quizdroid.data.datasource.QuizDataSource
import com.ottfstudio.quizdroid.data.model.QuizResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultQuizDataSource
    @Inject
    constructor(
        private val quizService: QuizService,
    ) : QuizDataSource {
        override suspend fun fetchTodayQuiz(date: String): List<QuizResponse> {
            val response = quizService.getTodayQuiz(date = "$FILTER_EQUALS$date")
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Failed to fetch quiz ${response.code()}")
            }
        }

        companion object {
            private const val FILTER_EQUALS = "eq."
        }
    }
