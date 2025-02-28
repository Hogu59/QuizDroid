package com.ottfstudio.quizdroid.domain.model

data class Record(
    val date: String,
    val quiz: Quiz,
    val selectedOption: Int,
    val isCorrect: Boolean,
    val isSolved: Boolean,
    val solvedResult: Int = calculateSolvedResult(isSolved, isCorrect),
) {
    companion object {
        private fun calculateSolvedResult(isSolved: Boolean, isCorrect: Boolean): Int {
            return when {
                !isSolved -> 0
                isCorrect -> 1
                else -> -1
            }
        }
    }
}
