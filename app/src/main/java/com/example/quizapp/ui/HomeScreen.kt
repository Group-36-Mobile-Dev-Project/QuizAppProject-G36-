package com.example.quizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Challenge Your Mind, Conquer the Game!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Choose your Category",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Category buttons with different colors
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CategoryButton("General Knowledge", Color(0xFFFFC107))
            CategoryButton("Sports", Color(0xFF03A9F4))
            CategoryButton("History", Color(0xFF4CAF50))
            CategoryButton("Science", Color(0xFFFF5722))
            CategoryButton("Entertainment", Color(0xFF9C27B0))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Game Explanation
        Text(
            text = "Greetings dear player! In this screen, you can select a quiz category, for questions most suitable to you! :) Good luck and have fun!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

// Reusable category button function
@Composable
fun CategoryButton(text: String, color: Color) {
    Button(
        onClick = { /* TODO: Navigate to quiz screen */ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(text = text, color = Color.White)
    }
}

// INFO !! //
// Displays "Challenge your mind, conquer the game."
// Shows quiz category buttons
// - general knownledge, sports, history, movies and videogames