package com.ottf.quizdroid.domain

data class Quiz(
    val id: Long,
    val category: String,
    val title: String,
    val writer: String,
    val level: String,
    val question: String,
    val options: List<String>,
)
