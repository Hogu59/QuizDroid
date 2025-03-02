package com.ottfstudio.quizdroid.data.datasource

import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

interface RecordDataSource {
    suspend fun fetchQuizRecord(date: String): QuizRecord?

    suspend fun insertQuizRecord(
        quizRecord: QuizRecord,
    )

    suspend fun deleteQuizRecord(date: String)
}
