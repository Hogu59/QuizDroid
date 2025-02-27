package com.ottf.quizdroid.data.repository

import com.ottf.quizdroid.data.datasource.DefaultQuizDataSource
import com.ottf.quizdroid.data.model.QuizResponse
import com.ottf.quizdroid.domain.model.Quiz
import com.ottf.quizdroid.domain.repository.QuizRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultQuizRepository
    @Inject
    constructor(
        private val dataSource: DefaultQuizDataSource,
    ) : QuizRepository {
        override suspend fun fetchTodayQuiz(date: String): Quiz {
            return dataSource.fetchTodayQuiz(date).first().toDomain()
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
