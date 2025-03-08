package com.ottfstudio.quizdroid.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.ottfstudio.quizdroid.data.local.RecordDao
import com.ottfstudio.quizdroid.data.local.RecordDatabase
import com.ottfstudio.quizdroid.data.local.converter.QuizConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideQuizDatabase(
        @ApplicationContext context: Context,
        quizConverter: QuizConverter,
    ): RecordDatabase {
        return Room.databaseBuilder(
            context,
            RecordDatabase::class.java,
            "quiz_database",
        )
            .addTypeConverter(quizConverter)
            .addMigrations(DatabaseMigrations.MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideQuizRecordDao(recordDatabase: RecordDatabase): RecordDao {
        return recordDatabase.recordDao()
    }

    @Provides
    @Singleton
    fun provideQuizConverter(gson: Gson): QuizConverter {
        return QuizConverter(gson)
    }
}

object DatabaseMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            try {
                db.execSQL("ALTER TABLE quiz_record ADD COLUMN consecutiveCount INTEGER NOT NULL DEFAULT 1")
            } catch (e: Exception) {
                Log.e("DatabaseMigrations", "Migration 1-2 failed: ${e.message}")
            }
        }
    }
}
