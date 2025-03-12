package com.example.simpleui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.simpleui.data.User
import com.example.simpleui.data.UserRoomDatabase
import com.example.simpleui.repository.UserRepository

class UserViewModel(application: Application): ViewModel()  {

    private val repository: UserRepository
    val userList: LiveData<List<User>>
    var userName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

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

    fun addUser(){
        repository.addUser(User(userName,email,password))
    }

    fun deleteUser(id: Int){
        repository.deleteUser(id)
    }
}