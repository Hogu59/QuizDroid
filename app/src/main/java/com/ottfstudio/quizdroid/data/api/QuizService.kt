package com.ottfstudio.quizdroid.data.api

import com.ottfstudio.quizdroid.BuildConfig
import com.ottfstudio.quizdroid.data.model.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface QuizService {
    @GET("/rest/v1/{table}?select=*")
    suspend fun getQuizzes(
        @Header("apikey") apiKey: String = BuildConfig.API_KEY,
        @Path("table") tableName: String = BuildConfig.TABLE_NAME,
    ): Response<List<QuizResponse>>

    @GET("/rest/v1/{table}")
    suspend fun getTodayQuiz(
        @Header("apikey") apiKey: String = BuildConfig.API_KEY,
        @Path("table") tableName: String = BuildConfig.TABLE_NAME,
        @Query("date") date: String,
    ): Response<List<QuizResponse>>
}
