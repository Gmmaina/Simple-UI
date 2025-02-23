package com.example.simpleui


sealed class AppRoutes(val route : String){
    object SplashScreen : AppRoutes("splashScreen")
    object LoginScreen : AppRoutes("loginScreen")
    object SignUpScreen : AppRoutes("signUpScreen")
    object ForgotPasswordScreen : AppRoutes("forgotPasswordScreen")
    object HomeScreen : AppRoutes("homeScreen")
}
