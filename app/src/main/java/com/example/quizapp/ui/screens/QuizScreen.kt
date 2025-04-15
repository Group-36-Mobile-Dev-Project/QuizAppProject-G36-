package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun QuizScreen(navController: NavController, category: String) {
    var currentQuestion by remember { mutableIntStateOf(1) }
    val totalQuestions = 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header Section
        Column {
            Text(
                text = category,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Question $currentQuestion/$totalQuestions",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // Question Content
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Sample text",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 32.dp)
            )

            // Answer Options
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("Option 1", "Option 2", "Option 3", "Option 4").forEach { option ->
                    OutlinedButton(
                        onClick = {  },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = option)
                    }
                }
            }
        }

        // Navigation Footer
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Back to Categories")
            }

            Button(onClick = {
                if(currentQuestion < totalQuestions) currentQuestion++
                else navController.popBackStack()
            }) {
                Text(if(currentQuestion < totalQuestions) "Next Question" else "Finish")
            }
        }
    }
}