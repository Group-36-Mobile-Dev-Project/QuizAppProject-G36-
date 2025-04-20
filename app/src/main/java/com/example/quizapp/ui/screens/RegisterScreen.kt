package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.viewmodel.AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var currentStep by remember { mutableStateOf(1) } // Step control

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Beetlequiz",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Challenge Your Mind, Conquer the Game!",
            fontSize = 14.sp,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (currentStep == 1) {  // **Step 1: Email Entry**
            Text(text = "Create an account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Enter your email to sign up for this app")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { currentStep = 2 }, // Go to next step
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "Continue", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Already have an account? Log in",
                color = Color.Blue,
                modifier = Modifier.clickable { navController.navigate("login") }
            )

        } else {  // **Step 2: Username & Password Entry**
            Text(text = "Complete your profile", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Enter your username and password to continue")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    authViewModel.registerUser(email, username, password) { success, errorMessage ->
                        if (success) {
                            navController.navigate("login") // Navigate to login on success
                        } else {
                            // Handle error (e.g., show a Toast or error message)
                            println("Registration failed: $errorMessage")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "Sign Up", color = Color.White)
            }
        }
    }
}

// !! INFO HERE !!

// Displays the "Beetlequiz!" greeting and tagline!
// it let's users enter their email, password!
// Calls Firebase Auth via AuthViewModel when clicking Register!
// Navigates to Login Screen if the user already has an account!
// Shows success & error messages!