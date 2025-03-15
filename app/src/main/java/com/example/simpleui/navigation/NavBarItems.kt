package com.example.simpleui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            icon = Icons.Filled.Home,
            route = "homeScreen"
        ),
        BarItem(
            title = "Users",
            icon = Icons.Filled.Person,
            route = "adminDashboard"
        ),
        BarItem(
            title = "Animation",
            icon = Icons.Filled.PlayArrow,
            route = "animationScreen"
        ),
        BarItem(
            title = "Settings",
            icon = Icons.Filled.Settings,
            route = "settingsScreen"
        )
    )
}

data class BarItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)