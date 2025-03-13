package com.example.simpleui.repository

import androidx.lifecycle.LiveData
import com.example.simpleui.data.User
import com.example.simpleui.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    val userList: LiveData<List<User>> = userDao.getAll()

    suspend fun addUser(user: User): String {
        return withContext(Dispatchers.IO) {
            try {
                val existingUser = userDao.getUserByEmailOrUsername(user.email, user.username)
                if (existingUser != null) {
                    throw IllegalArgumentException("Email or username already exists")
                }
                userDao.addUser(user)
                return@withContext "Account created Successfully!"
            } catch (e: IllegalArgumentException) {
                return@withContext "${e.message}"
            }

        }
    }

    suspend fun deleteUser(id: Int) {
        withContext(Dispatchers.IO) {
            userDao.delete(id)
        }
    }

    suspend fun login(email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.login(email, password)
        }
    }
}