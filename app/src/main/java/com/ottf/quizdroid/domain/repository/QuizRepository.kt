package com.ottf.quizdroid.domain.repository

import com.ottf.quizdroid.domain.model.Quiz

interface QuizRepository {
    suspend fun fetchTodayQuiz(date: String): Quiz
}
