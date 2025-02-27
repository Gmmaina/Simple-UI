package com.example.simpleui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(userEmail: String?) {
    val email = userEmail ?: ""
    Text(
        text = "Hello Buddy $email"
    )
}
