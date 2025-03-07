package com.example.simpleui.data

import com.example.simpleui.R


data class OnBoardingPage(
    val title: String,
    val description: String,
    val image: Int
)

val onboardingPage = listOf(
    OnBoardingPage("Welcome!", "This app helps you...", R.drawable.tales),
    OnBoardingPage("Easy Navigation", "Find what you need fast", R.drawable.stars),
    OnBoardingPage("Get Started", "Let's set up your account", R.drawable.avatar)
)
