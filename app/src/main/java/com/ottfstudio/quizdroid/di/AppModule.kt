package com.ottfstudio.quizdroid.di

import android.content.Context
import androidx.room.Room
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
            .addMigrations()
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
