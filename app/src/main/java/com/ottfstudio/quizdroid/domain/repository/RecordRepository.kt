package com.ottfstudio.quizdroid.domain.repository

import com.ottfstudio.quizdroid.domain.model.Record

interface RecordRepository {
    suspend fun fetchQuizRecord(date: String): Record?

    suspend fun fetchLatestConsecutiveSolvedCount(today: String): Int

    suspend fun fetchTotalSolvedCount(): Int

    suspend fun fetchTotalCorrectCount(): Int

    suspend fun insertQuizRecord(record: Record)

    suspend fun deleteQuizRecord(date: String)
}
