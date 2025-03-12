package com.example.simpleui.repository

import androidx.lifecycle.LiveData
import com.example.simpleui.data.User
import com.example.simpleui.data.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository (private val userDao: UserDao){
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val userList: LiveData<List<User>> = userDao.getAll()

    fun addUser(user: User){
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
        }
    }

    fun deleteUser(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            userDao.delete(id)
        }
    }
}