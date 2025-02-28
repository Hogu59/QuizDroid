package com.ottfstudio.quizdroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ottfstudio.quizdroid.data.local.converter.QuizConverter
import com.ottfstudio.quizdroid.data.model.entity.QuizRecord

@Database(entities = [QuizRecord::class], version = 1)
@TypeConverters(QuizConverter::class)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
}
