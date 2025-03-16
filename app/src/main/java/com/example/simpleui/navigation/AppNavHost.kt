package com.example.simpleui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleui.screens.AdminDashboard
import com.example.simpleui.screens.AnimationScreen
import com.example.simpleui.screens.ForgotPasswordScreen
import com.example.simpleui.screens.HomeScreen
import com.example.simpleui.screens.LoginScreen
import com.example.simpleui.screens.SettingsScreen
import com.example.simpleui.screens.SignUpScreen
import com.example.simpleui.screens.SplashScreen

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
            ForgotPasswordScreen(navController, email)
        }
        composable(AppRoutes.HomeScreen.route + "/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            HomeScreen(navController, email)
        }
        composable(AppRoutes.AdminDashboard.route + "/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            AdminDashboard(navController, viewModel = viewModel(), email)
        }
        composable(AppRoutes.AnimationScreen.route) {
            AnimationScreen(navController)
        }
        composable(AppRoutes.SettingsScreen.route) {
            SettingsScreen(navController)
        }
    }
}