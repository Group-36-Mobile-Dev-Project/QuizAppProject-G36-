package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.ui.theme.getCategoryColor

@Composable
fun ResultsScreen(navController: NavController, score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Result Icon
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Results",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Score Display
        Text(
            text = "$score/$totalQuestions",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Result Text
        Text(
            text = "Quiz Completed!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "You answered $score questions correctly",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Retry Button
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Retry Quiz", fontSize = 16.sp)
        }
    }
}

