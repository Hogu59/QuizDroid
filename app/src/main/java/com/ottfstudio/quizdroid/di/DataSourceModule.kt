package com.ottfstudio.quizdroid.di

import com.ottfstudio.quizdroid.data.datasource.QuizDataSource
import com.ottfstudio.quizdroid.data.datasource.RecordDataSource
import com.ottfstudio.quizdroid.data.local.LocalRecordDataSource
import com.ottfstudio.quizdroid.data.remote.DefaultQuizDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindQuizDataSource(defaultQuizDataSource: DefaultQuizDataSource): QuizDataSource

    @Binds
    fun bindRecordDataSource(defaultRecordDataSource: LocalRecordDataSource): RecordDataSource
}
