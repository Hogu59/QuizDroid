package com.ottfstudio.quizdroid.data.datasource

import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

interface RecordDataSource {
//    suspend fun fetchWeeklyRecord(startingDate: String, endingDate: String): Int
    suspend fun fetchQuizRecord(date: String): QuizRecord?

    suspend fun insertQuizRecord(
        quizRecord: QuizRecord,
    )
//    suspend fun insertWeeklyRecord(
//        startingDate: String,
//        endingDate: String,
//        solvedResult: Int,
//    )
}
