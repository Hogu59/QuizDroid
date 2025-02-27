package com.ottfstudio.quizdroid.di

import com.ottfstudio.quizdroid.data.datasource.DefaultQuizDataSource
import com.ottfstudio.quizdroid.data.datasource.QuizDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindQuizDataSource(defaultQuizDataSource: DefaultQuizDataSource): QuizDataSource
}
