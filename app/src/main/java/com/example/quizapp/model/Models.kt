package com.example.quizapp.model

import androidx.compose.ui.graphics.Color

data class Category(
    val name: String,
    val color: Color
)

data class Question(
    val id: String = "",
    val category: String = "",
    val question: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = ""
)