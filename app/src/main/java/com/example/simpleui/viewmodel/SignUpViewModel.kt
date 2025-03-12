package com.example.simpleui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpleui.navigation.AppRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
    }

    fun signUp(
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                //Validate name length
                if (name.length < 3) {
                    throw IllegalArgumentException("Name must be at least 3 characters long.")
                }

                // Check if any field is empty
                if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                    throw IllegalArgumentException("Please fill all fields.")
                }

                // Validate password length
                if (password.length < 6) {
                    throw IllegalArgumentException("Password must be at least 6 characters long.")
                }

                // Validate password and confirm password match
                if (password != confirmPassword) {
                    throw IllegalArgumentException("Passwords do not match.")
                }

                // Validate email format
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    throw IllegalArgumentException("Invalid email format.")
                }

                // Simulate signup delay
                delay(1500)

                // Simulated successful signup
                _message.value = "Account created Successfully!"
                navController.navigate(AppRoutes.HomeScreen.route + "/$email") {
                    popUpTo(AppRoutes.SignUpScreen.route) {
                        inclusive = true
                    }
                }
            }catch (e: IllegalArgumentException) {
                _message.value = e.message ?: "An error occurred"
            } catch (e: Exception) {
                _message.value = "Something went wrong. Please try again."
            }
        }
    }
}