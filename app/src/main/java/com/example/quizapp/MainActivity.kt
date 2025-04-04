package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.quizapp.ui.*
import com.example.quizapp.ui.screens.*
import com.example.quizapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Test")
            val authViewModel = remember { AuthViewModel() }
            QuizAppNavigation(authViewModel)
        }
    }
}

@Composable
fun QuizAppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "register") {
        composable("register") { RegisterScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("home") { HomeScreen(navController) }
    }
}

// INFO !! //

// Jetpack Compose Navigation set up.
// Navigating between "Register", "Login" and "Home" screens.
// Opens up the "Register screen" when the app launches.