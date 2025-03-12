package com.example.simpleui.repository

import androidx.lifecycle.LiveData
import com.example.simpleui.data.User
import com.example.simpleui.data.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository (private val userDao: UserDao){
    val userList: LiveData<List<User>> = userDao.getAll()

    suspend fun addUser(user: User): String {
       return withContext(Dispatchers.IO) {
           val existingUser = userDao.getUserByEmailOrUsername(user.email, user.username)
           if (existingUser != null) {
               throw IllegalArgumentException("Email or username already exists")
           }
           userDao.addUser(user)
           return@withContext "Account created Successfully!"
        }
    }

    suspend fun deleteUser(id: Int){
        withContext(Dispatchers.IO) {
            userDao.delete(id)
        }
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email,password)
    }
}