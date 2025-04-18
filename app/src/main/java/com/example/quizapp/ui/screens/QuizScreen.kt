package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.getCategoryColor
import androidx.lifecycle.viewmodel.compose.viewModel
import QuizViewModel

@Composable
fun QuizScreen(navController: NavController, category: String) {
    val quizViewModel: QuizViewModel = viewModel()
    val questions by quizViewModel.questions.collectAsState()
    val isLoading by quizViewModel.isLoading.collectAsState()
    val currentQuestionIndex by quizViewModel.currentQuestionIndex

    LaunchedEffect(category) {
        quizViewModel.getQuestionsByCategory(category)
    }

    if (isLoading) {
        CircularProgressIndicator()
        return
    }

    if (questions.isEmpty()) {
        Text("No questions available")
        return
    }

    val currentQuestion = questions[currentQuestionIndex]
    val totalQuestions = questions.size
    val categoryColor = getCategoryColor(category)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header Section
        Spacer(modifier = Modifier.height(30.dp))
        Column {
            Text(
                text = category,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = categoryColor
            )
            Text(
                text = "Question ${currentQuestionIndex + 1}/$totalQuestions",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // Question Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentQuestion.question,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 32.dp),
                textAlign = TextAlign.Center
            )
        }

        // Answer Options Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            items(currentQuestion.options) { option ->
                Button(
                    onClick = {
                        if (option == currentQuestion.correctAnswer) {
                            quizViewModel.score.intValue++
                        }

                        if (currentQuestionIndex < totalQuestions - 1) {
                            quizViewModel.currentQuestionIndex.intValue++
                        } else {
                            navController.navigate("results/${quizViewModel.score.intValue}/$totalQuestions")
                        }
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = categoryColor.copy(alpha = 0.2f),
                        contentColor = categoryColor
                    )
                ) {
                    Text(
                        text = option,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Navigation Footer
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = categoryColor
                )
            ) {
                Text("Quit")
            }
        }
    }
}