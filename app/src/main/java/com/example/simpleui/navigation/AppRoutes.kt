package com.example.simpleui.navigation


sealed class AppRoutes(val route: String) {
    object SplashScreen : AppRoutes("splashScreen")
    object LoginScreen : AppRoutes("loginScreen")
    object SignUpScreen : AppRoutes("signUpScreen")
    object ForgotPasswordScreen : AppRoutes("forgotPasswordScreen")
    object HomeScreen : AppRoutes("homeScreen")
    object AdminDashboard : AppRoutes("adminDashboard")
    object AnimationScreen : AppRoutes("animationScreen")
    object SettingsScreen : AppRoutes("settingsScreen")
}
