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
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun QuizScreen(navController: NavController, category: String) {
    val quizViewModel: QuizViewModel = viewModel()
    val questions by quizViewModel.questions.collectAsState()
    val isLoading by quizViewModel.isLoading.collectAsState()
    val currentQuestionIndex by quizViewModel.currentQuestionIndex
    var timeLeft by remember { mutableIntStateOf(15) }

    LaunchedEffect(category) {
        quizViewModel.getQuestionsByCategory(category)
    }

    // Logic for timer
    LaunchedEffect(currentQuestionIndex) {
        timeLeft = 15
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        if (currentQuestionIndex < questions.size - 1) {
            quizViewModel.currentQuestionIndex.intValue++
        } else {
            navController.navigate("results/${quizViewModel.score.intValue}/${questions.size}")
        }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
        }


        // Question Section
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currentQuestion.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = currentQuestion.imageUrl,
                    contentDescription = "Question image",
                    modifier = Modifier
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = currentQuestion.question,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Horizontal padding
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                maxLines = 3,
                softWrap = true,
                minLines = 1,
                fontSize = when (currentQuestion.question.length) {
                    in 0..50 -> 22.sp
                    in 51..100 -> 20.sp
                    else -> 18.sp
                }
            )
        }

        // Timer Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(categoryColor.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$timeLeft s",
                    color = if (timeLeft <= 5) Color.Red else categoryColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Answer Options Grid Section
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
                            navController.navigate("results/${category}/${quizViewModel.score.intValue}/$totalQuestions")
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
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = when {
                            option.length > 25 -> 14.sp
                            option.length > 15 -> 16.sp
                            else -> 18.sp
                        },
                        maxLines = 2,
                        softWrap = true,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Navigation Footer Section
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