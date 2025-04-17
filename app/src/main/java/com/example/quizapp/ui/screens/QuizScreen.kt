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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.getCategoryColor

@Composable
fun QuizScreen(navController: NavController, category: String) {
    var currentQuestion by remember { mutableIntStateOf(1) }
    val totalQuestions = 10
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
                text = "Question $currentQuestion/$totalQuestions",
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
                text = "Sample text",
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
            items(4) { index ->
                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = categoryColor.copy(alpha = 0.2f),
                        contentColor = categoryColor
                    )
                ) {
                    Text(
                        text = "${index + 1}",
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