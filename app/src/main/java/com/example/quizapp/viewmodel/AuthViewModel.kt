package com.example.quizapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await


class AuthViewModel : ViewModel() {
    val currentUser: FirebaseUser?
        get() = FirebaseAuth.getInstance().currentUser

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    fun fetchUsername(userId: String) {
        viewModelScope.launch {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    _username.value = document.getString("username")
                }
                .addOnFailureListener { e ->
                    Log.e("AuthViewModel", "Error fetching username", e)
                }
        }
    }

    fun registerUser(email: String, password: String, username: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            val user = hashMapOf(
                                "username" to username,
                                "email" to email
                            )
                            db.collection("users").document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    onResult(true, "Registration successful!")
                                }
                                .addOnFailureListener { e ->
                                    Log.e("AuthViewModel", "Error saving user data", e)
                                    onResult(false, "Failed to save user data.")
                                }
                        }
                    } else {
                        onResult(false, task.exception?.message ?: "Registration failed.")
                    }
                }
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, "Login successful!")
                    } else {
                        onResult(false, task.exception?.message ?: "Login failed.")
                    }
                }
        }
    }

    fun logout() {
        auth.signOut()
    }
    suspend fun deleteAccount(): Result<Unit> {
        return try {
            FirebaseAuth.getInstance().currentUser?.delete()?.await()
            logout()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun sendPasswordResetEmail(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                // Optional: Handle completion if needed
            }
    }
}