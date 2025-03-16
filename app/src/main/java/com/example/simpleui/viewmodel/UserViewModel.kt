package com.example.simpleui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.simpleui.data.User
import com.example.simpleui.data.UserRoomDatabase
import com.example.simpleui.navigation.AppRoutes
import com.example.simpleui.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val userList: LiveData<List<User>>

    var userName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var isSuccess by mutableStateOf(false)
    var signUpMessage by mutableStateOf("")
    var loginMessage by mutableStateOf("")


    init {
        val userDb = UserRoomDatabase.getInstance(application)
        val userDao = userDb.userDao()
        repository = UserRepository(userDao)
        userList = repository.userList
    }

    fun onNameChange(newName: String) {
        userName = newName
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

    fun addUser(navController: NavHostController) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            signUpMessage = "Please fill in all fields"
            isSuccess = false
            return
        }
        if (password.length < 6) {
            signUpMessage = "Password must be at least 6 characters"
            isSuccess = false
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpMessage = "Invalid email address"
            isSuccess = false
            return
        }
        if (password != confirmPassword) {
            signUpMessage = "Passwords do not match"
            isSuccess = false
            return
        }

        viewModelScope.launch {
            try {
                val result = repository.addUser(
                    User(
                        username = userName,
                        email = email,
                        password = password
                    )
                )
                signUpMessage = result
                isSuccess = result == "Account created Successfully!"

                if (isSuccess) {
                    delay(2000)
                    navController.navigate(AppRoutes.HomeScreen.route + "/${email}") {
                        popUpTo(AppRoutes.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            } catch (e: Exception) {
                signUpMessage = e.message ?: "Error occurred"
            } catch (e: IllegalArgumentException) {
                signUpMessage = e.message ?: "Something went wrong. please try again."
            }
        }
    }

    fun login(navController: NavController) {
        if (email.isEmpty() || password.isEmpty()) {
            loginMessage = "Please fill in all fields"
            isSuccess = false
            return
        }
        viewModelScope.launch {
            val user = repository.login(email, password)
            if (user != null) {
                loginMessage = "Login Successful"
                isSuccess = true
                delay(2000)
                navController.navigate(AppRoutes.AdminDashboard.route + "/${email}") {
                    popUpTo(AppRoutes.LoginScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                loginMessage = "Invalid email or password"
                isSuccess = false
            }
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return userList
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            repository.deleteUser(id)
        }
    }
}