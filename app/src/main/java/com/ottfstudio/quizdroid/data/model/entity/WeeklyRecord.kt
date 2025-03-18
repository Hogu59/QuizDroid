package com.ottfstudio.quizdroid.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_record")
data class WeeklyRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val weekStartDate: String,
    val weekEndDate: String,
    val correctAnswerCount: Int,
)
