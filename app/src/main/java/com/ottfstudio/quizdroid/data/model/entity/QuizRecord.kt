package com.ottfstudio.quizdroid.data.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ottfstudio.quizdroid.domain.model.Quiz

@Entity(
    tableName = "quiz_record",
    indices = [Index(value = ["date"], unique = true)],
)
data class QuizRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val date: String,
    val quiz: Quiz,
    val selectedOption: Int,
    val solvedResult: Int,
    val isCorrect: Boolean,
    val isSolved: Boolean,
)
