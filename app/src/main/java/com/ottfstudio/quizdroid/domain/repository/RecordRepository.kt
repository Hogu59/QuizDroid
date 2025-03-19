package com.ottfstudio.quizdroid.domain.repository

import com.ottfstudio.quizdroid.domain.model.Record

interface RecordRepository {
    suspend fun fetchQuizRecord(date: String): Record?

    /***오늘 날짜를 기준으로 최근 연속 정답 횟수를 조회합니다.
     *
     * @param today 오늘 날짜 (형식: "yyyy-MM-dd")
     * @return 연속 정답 횟수
     */
    suspend fun fetchLatestConsecutiveSolvedCount(today: String): Int

    suspend fun fetchTotalSolvedCount(): Int

    suspend fun fetchTotalCorrectCount(): Int

    /*** 두 날짜 사이에서 퀴즈 푼 총 횟수를 조회합니다.
     *  @param startDate : 시작 날짜 (형식: "yyyy.MM.dd")
     *  @param endDate : 종료 날짜 (형식: "yyyy.MM.dd")
     *  @return 퀴즈 푼 총 횟수
     */
    suspend fun fetchSolvedCountByDateRange(startDate: String, endDate: String): Int

    suspend fun deleteQuizRecord(date: String)

    suspend fun insertQuizRecord(record: Record)
}
