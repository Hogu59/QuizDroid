package com.ottf.quizdroid.di

import com.ottf.quizdroid.BuildConfig
import com.ottf.quizdroid.data.api.QuizService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        val client =
            OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        val interceptor =
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        addInterceptor(interceptor)
                    }
                    connectTimeout(30, TimeUnit.SECONDS)
                    readTimeout(20, TimeUnit.SECONDS)
                    writeTimeout(25, TimeUnit.SECONDS)
                }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuizService(retrofit: Retrofit): QuizService = retrofit.create(
        QuizService::class.java,
    )
}
