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
        startDestination = "login"
    ) {
        composable("register") { RegisterScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("home") { HomeScreen(navController, authViewModel) }
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
        composable(
            "results/{score}/{total}",
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            ResultsScreen(
                navController = navController,
                score = backStackEntry.arguments?.getInt("score") ?: 0,
                totalQuestions = backStackEntry.arguments?.getInt("total") ?: 10
            )
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}

// INFO !! //

// Jetpack Compose Navigation set up.
// Navigating between "Register", "Login" and "Home" screens.
// Opens up the "Register screen" when the app launches.