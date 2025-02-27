package com.ottf.quizdroid.data.repository

import com.ottf.quizdroid.data.datasource.QuizDataSource
import com.ottf.quizdroid.data.model.QuizResponse
import com.ottf.quizdroid.domain.model.Quiz
import com.ottf.quizdroid.domain.repository.QuizRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultQuizRepository
    @Inject
    constructor(
        private val dataSource: QuizDataSource,
    ) : QuizRepository {
        override suspend fun fetchTodayQuiz(date: String): Quiz {
            val quizzes = dataSource.fetchTodayQuiz(date)
            return if (quizzes.isNotEmpty()) {
                quizzes.first().toDomain()
            } else {
                throw NoSuchElementException("오늘의 퀴즈를 찾을 수 없습니다: $date")
            }
        }
    }

private fun QuizResponse.toDomain(): Quiz {
    return Quiz(
        id = id,
        category = category,
        title = title,
        writer = writer,
        level = level,
        question = question,
        answer = answer,
        description = description,
        options = options,
    )
}
