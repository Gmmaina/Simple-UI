package com.example.simpleui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier, email: String?) {
    Text(
        text = "Hello Buddy $email"
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(email = "")
}