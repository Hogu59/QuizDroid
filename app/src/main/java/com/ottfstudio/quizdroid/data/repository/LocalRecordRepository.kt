package com.ottfstudio.quizdroid.data.repository

import androidx.room.Transaction
import com.ottfstudio.quizdroid.data.datasource.RecordDataSource
import com.ottfstudio.quizdroid.data.mapper.toDomain
import com.ottfstudio.quizdroid.data.mapper.toEntity
import com.ottfstudio.quizdroid.domain.model.Record
import com.ottfstudio.quizdroid.domain.repository.RecordRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRecordRepository
    @Inject
    constructor(
        private val recordDataSource: RecordDataSource,
    ) : RecordRepository {
        override suspend fun fetchQuizRecord(date: String): Record? {
            return recordDataSource.fetchQuizRecord(date)?.toDomain()
        }

        @Transaction
        override suspend fun insertQuizRecord(
            record: Record,
        ) {
            val consecutiveCount = fetchLatestConsecutiveSolvedCount(record.date) + 1
            recordDataSource.insertQuizRecord(
                record.toEntity(consecutiveCount = consecutiveCount),
            )
        }

        override suspend fun deleteQuizRecord(date: String) {
            recordDataSource.deleteQuizRecord(date)
        }

        /***
         * 오늘 일자를 기준으로 연속풀이를 진행한 날짜를 계산하는 함수
         *  @param today 오늘 날짜 (yyyy.MM.dd)
         *  @return 연속풀이를 진행한 날짜의 개수
         */
        override suspend fun fetchLatestConsecutiveSolvedCount(today: String): Int {
            val latestRecord = recordDataSource.fetchLatestRecord()
            return when {
                latestRecord == null -> 0
                latestRecord.date == today -> latestRecord.consecutiveCount
                else -> {
                    when {
                        isConsecutiveDate(today, latestRecord.date) -> latestRecord.consecutiveCount
                        else -> 0
                    }
                }
            }
        }

        override suspend fun fetchTotalSolvedCount(): Int {
            return recordDataSource.fetchTotalRecordCount()
        }

        override suspend fun fetchTotalCorrectCount(): Int {
            return recordDataSource.fetchTotalCorrectCount()
        }

        override suspend fun fetchSolvedCountByDateRange(startDate: String, endDate: String): Int {
            return recordDataSource.fetchSolvedCountByDateRange(startDate, endDate)
        }

        private fun isConsecutiveDate(current: String, previous: String): Boolean {
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

            val todayDate = LocalDate.parse(current, formatter)
            val previousDate = LocalDate.parse(previous, formatter)

            return todayDate.minusDays(1) == previousDate
        }
    }
