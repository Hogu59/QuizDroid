package com.ottfstudio.quizdroid.data.repository

import com.ottfstudio.quizdroid.data.datasource.RecordDataSource
import com.ottfstudio.quizdroid.data.mapper.toDomain
import com.ottfstudio.quizdroid.data.mapper.toEntity
import com.ottfstudio.quizdroid.domain.model.Record
import com.ottfstudio.quizdroid.domain.repository.RecordRepository
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

        override suspend fun insertQuizRecord(
            record: Record,
        ) {
            recordDataSource.insertQuizRecord(
                record.toEntity(),
            )
        }
    }
