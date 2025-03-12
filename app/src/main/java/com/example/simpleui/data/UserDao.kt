package com.example.simpleui.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE Userid = :id")
    fun getById(id: Int): LiveData<User>

    @Insert
    fun addUser(user: User)

    @Query("DELETE FROM users WHERE Userid = :id")
    fun delete(id: Int)

}