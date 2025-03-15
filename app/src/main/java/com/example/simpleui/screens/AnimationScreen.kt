package com.example.simpleui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simpleui.R
import com.example.simpleui.navigation.BottomNavigationBar

@Composable
fun AnimationScreen(navController: NavController) {
    var colorState by remember { mutableStateOf(Color.DarkGray) }
    var text by remember { mutableStateOf("Gray") }

    val animatedColor by animateColorAsState(
        targetValue = colorState,
        animationSpec = tween(5000)
    )

    Scaffold(
        modifier = Modifier.background(colorResource(R.color.WhiteSmoke)), // ✅ Background applied here
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp)
                    .background(animatedColor) // ✅ Color animation works here
            )
            Button(
                onClick = {
                    colorState = if (colorState == Color.Red) {
                        text = "Gray"
                        Color.DarkGray
                    } else {
                        text = "Red"
                        Color.Red
                    }
                }
            ) {
                Text(text = text)
            }
        }
    }
}


@Preview
@Composable
fun AnimationScreenPreview() {
    AnimationScreen(navController = NavController(LocalContext.current))
}
