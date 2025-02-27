package com.ottfstudio.quizdroid.data.local

import com.ottfstudio.quizdroid.data.datasource.RecordDataSource
import com.ottfstudio.quizdroid.data.model.entity.QuizRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRecordDataSource
    @Inject
    constructor(
        private val database: RecordDatabase,
    ) : RecordDataSource {
        private val recordDao = database.recordDao()

        override suspend fun fetchQuizRecord(date: String): QuizRecord? {
            return recordDao.fetchQuizRecordByDate(date)
        }

        override suspend fun insertQuizRecord(quizRecord: QuizRecord) {
            recordDao.insertQuizRecord(quizRecord)
        }
    }
