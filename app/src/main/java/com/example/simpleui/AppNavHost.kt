package com.example.simpleui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route
    ) {
        composable(AppRoutes.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(AppRoutes.LoginScreen.route) {
            LoginScreen()
        }
        composable(AppRoutes.SignUpScreen.route) {
            SignUpScreen()
        }
        composable(AppRoutes.ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
    }
}