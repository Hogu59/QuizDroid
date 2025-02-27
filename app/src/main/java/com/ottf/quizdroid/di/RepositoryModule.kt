package com.ottf.quizdroid.di

import com.ottf.quizdroid.data.repository.DefaultQuizRepository
import com.ottf.quizdroid.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindQuizRepository(defaultQuizRepository: DefaultQuizRepository): QuizRepository
}
