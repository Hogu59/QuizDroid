package com.ottfstudio.quizdroid.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ottfstudio.quizdroid.domain.model.Quiz

@ProvidedTypeConverter
class QuizConverter(private val gson: Gson) {
    @TypeConverter
    fun fromQuiz(quiz: Quiz): String {
        return gson.toJson(quiz)
    }

    @TypeConverter
    fun toQuiz(quizJson: String): Quiz {
        val type = object : TypeToken<Quiz>() {}.type
        return gson.fromJson(quizJson, type)
    }
}
