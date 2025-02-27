package com.ottfstudio.quizdroid.data.datasource

import com.ottfstudio.quizdroid.data.model.QuizResponse

interface QuizDataSource {
    suspend fun fetchTodayQuiz(date: String): List<QuizResponse>
}
