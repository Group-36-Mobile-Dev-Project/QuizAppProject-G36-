package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val currentUser by remember { mutableStateOf(authViewModel.currentUser) }
    val username by authViewModel.username.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currentUser != null) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp),
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

                // Delete Account Button
                Button(
                    onClick = {
                        scope.launch {
                            authViewModel.deleteAccount().fold(
                                onSuccess = {
                                    navController.popBackStack("login", inclusive = false)
                                },
                                onFailure = { e ->
                                    snackbarHostState.showSnackbar(
                                        "Delete failed: ${e.message ?: "Unknown error"}"
                                    )
                                }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete Account", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Reset Password Button
                Button(
                    onClick = {
                        currentUser?.email?.let { email ->
                            authViewModel.sendPasswordResetEmail(email)
                            scope.launch {
                                snackbarHostState.showSnackbar("Password reset email sent")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Reset Password", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Logout Button
                Button(
                    onClick = {
                        authViewModel.logout()
                        navController.popBackStack("login", inclusive = false)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Log Out", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Back to Home Button
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

private fun formatTimestamp(timestamp: Long?): String {
    return timestamp?.let {
        SimpleDateFormat("MMM d, yyyy 'at' h:mm a", Locale.getDefault())
            .format(Date(it))
    } ?: "Unknown"
}