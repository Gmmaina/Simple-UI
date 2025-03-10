package com.example.simpleui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.simpleui.navigation.AppRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    private val _isSuccess = mutableStateOf(false)
    val isSuccess: State<Boolean> = _isSuccess


    private val _message = mutableStateOf("")
    val message: State<String> = _message

    private val _showMessage = mutableStateOf(false)
    val showMessage: State<Boolean> = _showMessage

    fun onEmailChange(newEmail: String){
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
    }

    fun login(navController: NavController, email: String, password: String) {
        if( email.isNotEmpty() && password.isNotEmpty()) {
            _message.value = "Logged in successfully!"
            _isSuccess.value = true
            _showMessage.value = true

            viewModelScope.launch {
                delay(1500)
                navController.navigate(AppRoutes.HomeScreen.route + "/${_email.value}")
            }
        } else {
            _message.value = "Please fill all fields!"
            _isSuccess.value = false
            _showMessage.value = true
        }
    }
}