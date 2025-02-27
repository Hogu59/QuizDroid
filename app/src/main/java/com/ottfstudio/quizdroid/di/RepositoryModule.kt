package com.ottfstudio.quizdroid.di

import com.ottfstudio.quizdroid.data.repository.DefaultQuizRepository
import com.ottfstudio.quizdroid.data.repository.LocalRecordRepository
import com.ottfstudio.quizdroid.domain.repository.QuizRepository
import com.ottfstudio.quizdroid.domain.repository.RecordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindQuizRepository(defaultQuizRepository: DefaultQuizRepository): QuizRepository

    @Binds
    fun bindRecordRepository(defaultRecordRepository: LocalRecordRepository): RecordRepository
}
