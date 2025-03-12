package com.example.simpleui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Userid")
    var id: Int = 0
    @ColumnInfo(name = "username")
    var username: String? = ""
    @ColumnInfo(name = "email")
    var email: String? = ""
    @ColumnInfo(name = "password")
    var password: String? = ""

    constructor(){}

    constructor(userName: String, email: String, password: String){
        this.username = userName
        this.email = email
        this.password = password
    }
}