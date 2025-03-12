package com.example.simpleui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.simpleui.navigation.AppRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set


    private val _message = mutableStateOf("")
    val message: State<String> = _message


    fun onEmailChange(newEmail: String){
        email = newEmail
    }

    fun onPasswordChange(newPassword: String){
        password = newPassword
    }

    fun login(navController: NavController) {
        viewModelScope.launch {
            try {
                // Check if fields are empty
                if (email.isBlank() || password.isBlank()){
                    throw IllegalArgumentException("Please fill all fields.")
                }
                // Validate email format with simple regex
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    throw IllegalArgumentException("Invalid email format")
                }

                // Simulate authentication delay
                delay(1500)

                // Simulated successful Login
                _message.value = "Logged in Successfully"
                navController.navigate(AppRoutes.HomeScreen.route + "/$email"){
                    popUpTo(AppRoutes.LoginScreen.route) {
                        inclusive = true
                    }
                }

            }catch (e: IllegalArgumentException){
                _message.value = e.message ?: "An error occurred"
            }catch (e: Exception){
                _message.value = "Something went wrong. Please try again."
            }
        }
    }
}