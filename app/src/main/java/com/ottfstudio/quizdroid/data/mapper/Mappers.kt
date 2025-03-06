package com.ottfstudio.quizdroid.data.mapper

import com.ottfstudio.quizdroid.data.model.entity.QuizRecord
import com.ottfstudio.quizdroid.domain.model.Record

fun QuizRecord.toDomain(): Record =
    Record(
        date = date,
        quiz = quiz,
        selectedOption = selectedOption,
        solvedResult = solvedResult,
        isCorrect = isCorrect,
        isSolved = isSolved,
    )

fun Record.toEntity(consecutiveCount: Int): QuizRecord =
    QuizRecord(
        date = date,
        quiz = quiz,
        selectedOption = selectedOption,
        solvedResult = solvedResult,
        isCorrect = isCorrect,
        isSolved = isSolved,
        consecutiveCount = consecutiveCount,
    )
