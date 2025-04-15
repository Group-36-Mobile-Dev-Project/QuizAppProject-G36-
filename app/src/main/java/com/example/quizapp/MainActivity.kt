package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
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

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("register") { RegisterScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("home") { HomeScreen(navController) }
        composable(
            "quiz/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            QuizScreen(
                navController = navController,
                category = backStackEntry.arguments?.getString("category") ?: "General"
            )
        }
    }
}

// INFO !! //

// Jetpack Compose Navigation set up.
// Navigating between "Register", "Login" and "Home" screens.
// Opens up the "Register screen" when the app launches.