package com.ottfstudio.quizdroid.domain.repository

import com.ottfstudio.quizdroid.domain.model.Quiz

interface QuizRepository {
    suspend fun fetchTodayQuiz(date: String): Quiz
}
