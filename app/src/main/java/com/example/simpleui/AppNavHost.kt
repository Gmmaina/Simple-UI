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
            LoginScreen(navController)
        }
        composable(AppRoutes.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(AppRoutes.ForgotPasswordScreen.route + "/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ForgotPasswordScreen(email)
        }
        composable(AppRoutes.HomeScreen.route) {
            HomeScreen(email = "")
        }
    }
}