package com.ottf.quizdroid.di

import com.ottf.quizdroid.data.datasource.DefaultQuizDataSource
import com.ottf.quizdroid.data.datasource.QuizDataSource
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
