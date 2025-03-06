package com.ottfstudio.quizdroid.domain.repository

import com.ottfstudio.quizdroid.domain.model.Record

interface RecordRepository {
    suspend fun fetchQuizRecord(date: String): Record?

    /**
     * 오늘 날짜를 기준으로 최근 연속 정답 횟수를 조회합니다.
     *
     * @param today 오늘 날짜 (형식: "yyyy-MM-dd")
     * @return 연속 정답 횟수
     */
    suspend fun fetchLatestConsecutiveSolvedCount(today: String): Int

    suspend fun fetchTotalSolvedCount(): Int

    suspend fun fetchTotalCorrectCount(): Int

    suspend fun insertQuizRecord(record: Record)

    suspend fun deleteQuizRecord(date: String)
}
