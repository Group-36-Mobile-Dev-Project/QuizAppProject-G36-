package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val currentUser by remember { mutableStateOf(authViewModel.currentUser) }
    val username by authViewModel.username.collectAsState()

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let { authViewModel.fetchUsername(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentUser != null) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            UserInfoItem(label = "Username", value = username ?: "Loading...")
            UserInfoItem(label = "Email", value = currentUser!!.email ?: "No email")
            UserInfoItem(
                label = "Account Created",
                value = formatTimestamp(currentUser!!.metadata?.creationTimestamp)
            )
            UserInfoItem(
                label = "Last Sign In",
                value = formatTimestamp(currentUser!!.metadata?.lastSignInTimestamp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    authViewModel.logout()
                    navController.popBackStack("login", inclusive = false)
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Log Out", fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Back to Home", fontSize = 18.sp)
            }
        } else {
            Text("No user logged in", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun UserInfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            fontSize = 14.sp
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

// Replace the existing formatTimestamp function
private fun formatTimestamp(timestamp: Long?): String {
    return timestamp?.let {
        SimpleDateFormat("MMM d, yyyy 'at' h:mm a", Locale.getDefault())
            .format(Date(it))
    } ?: "Unknown"
}
