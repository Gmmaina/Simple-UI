package com.example.simpleui.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PopUpMessage(message: String, isSuccess: Boolean) {
    if (message.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
                .background(if (isSuccess) Color(0xFF4CAF50) else Color(0xFFD32F2F)) // Green for success, Red for error
        ) {
            Text(
                text = message,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}