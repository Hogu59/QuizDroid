package com.ottfstudio.quizdroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuizRecord(quizRecord: QuizRecord)

    @Query("SELECT * FROM quiz_record WHERE date = :date")
    suspend fun fetchQuizRecordByDate(date: String): QuizRecord?
}
