package com.ottfstudio.quizdroid.data.datasource

import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

interface RecordDataSource {
    suspend fun fetchQuizRecord(date: String): QuizRecord?

    suspend fun fetchLatestRecord(): QuizRecord?

    suspend fun fetchTotalRecordCount(): Int

    suspend fun fetchTotalCorrectCount(): Int

    suspend fun insertQuizRecord(
        quizRecord: QuizRecord,
    )

    suspend fun deleteQuizRecord(date: String)
}
