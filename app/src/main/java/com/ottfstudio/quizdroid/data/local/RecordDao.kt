package com.ottfstudio.quizdroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

// TODO: 테이블 분리 고민해볼 것
// https://github.com/Hogu59/QuizDroid/pull/21#discussion_r1973712499

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuizRecord(quizRecord: QuizRecord)

    @Query("SELECT * FROM quiz_record ORDER BY id DESC LIMIT 1")
    suspend fun fetchLatestRecord(): QuizRecord?

    @Query("SELECT COUNT(*) FROM quiz_record")
    suspend fun fetchTotalRecordCount(): Int

    @Query("SELECT COUNT(*) FROM quiz_record WHERE isCorrect = 1")
    suspend fun fetchTotalCorrectCount(): Int

    @Query("SELECT * FROM quiz_record WHERE date = :date")
    suspend fun fetchQuizRecordByDate(date: String): QuizRecord?

    @Query("DELETE FROM quiz_record WHERE date = :date")
    suspend fun deleteQuizRecordByDate(date: String)
}
