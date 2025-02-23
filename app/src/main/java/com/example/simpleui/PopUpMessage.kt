package com.example.simpleui

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
    if (message.isNotEmpty()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
                .background(if (isSuccess) Color.Green.copy(0.7f) else Color.Red.copy(0.7f))
        ){
            Text(
                text = message,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}