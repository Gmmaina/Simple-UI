package com.example.simpleui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simpleui.data.User

class UserViewModel: ViewModel()  {
    val users = mutableListOf<User>(User("Alice", 30), User("Bob", 40))
    var username by mutableStateOf("")
    var age by mutableIntStateOf(0)

    fun addUser() {
        users.add(User(username, age))
    }
    fun removeUser(user: User) {
        users.remove(user)
    }

    fun updateUsername(newUsername: String) {
        username = newUsername
    }
    fun updateAge(newAge: String) {
        age = newAge.toIntOrNull() ?:age
    }

}