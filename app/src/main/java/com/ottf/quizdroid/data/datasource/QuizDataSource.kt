package com.ottf.quizdroid.data.datasource

import com.ottf.quizdroid.data.model.QuizResponse

interface QuizDataSource {
    suspend fun fetchTodayQuizzes(): List<QuizResponse>

    suspend fun fetchTodayQuiz(date: String): List<QuizResponse>
}
