package com.ottfstudio.quizdroid.domain.repository

import com.ottfstudio.quizdroid.domain.model.Quiz

interface QuizRepository {
    /**
     * 지정된 날짜에 해당하는 퀴즈를 가져옵니다.
     *
     * @param date 조회할 날짜 (형식: "yyyy-MM-dd")
     * @return 해당 날짜의 퀴즈
     */
    suspend fun fetchTodayQuiz(date: String): Quiz
}
