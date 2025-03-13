package com.example.simpleui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.simpleui.data.OnBoardingPage
import com.example.simpleui.data.onboardingPage
import com.example.simpleui.navigation.AppRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { onboardingPage.size })
    val scope = rememberCoroutineScope()

    LocalContext.current
    // Auto-scroll logic
    LaunchedEffect(pagerState.currentPage) {
        while (pagerState.currentPage < onboardingPage.size - 1) {
            delay(3000) // Delay between auto-scrolls
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
        // Navigate to SignUp screen after the last page
        delay(3000)
        navController.navigate(AppRoutes.SignUpScreen.route) {
            popUpTo(AppRoutes.SplashScreen.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        // Skip Button (positioned at the top-right corner)
        TextButton(
            onClick = {
                navController.navigate(AppRoutes.SignUpScreen.route) {
                    popUpTo(AppRoutes.SplashScreen.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp)
                .zIndex(1f)
        ) {
            Text(text = "Skip", color = Color.Black)
        }

        // Onboarding Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Onboarding Pages
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingScreen(onboardingPage = onboardingPage[page])
            }

            // Custom Pager Indicator
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }

            // Navigation Buttons (Previous and Next)
            PagerIndicatorWithButtons(
                pagerState = pagerState,
                onNext = {
                    scope.launch {
                        if (pagerState.currentPage == onboardingPage.size - 1) {
                            navController.navigate(AppRoutes.SignUpScreen.route) {
                                popUpTo(AppRoutes.SplashScreen.route) { inclusive = true }
                            }
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },

                onPrevious = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            )
        }
    }
}

@Composable
fun OnboardingScreen(onboardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(onboardingPage.image),
            contentDescription = onboardingPage.title,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = onboardingPage.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = onboardingPage.description,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun PagerIndicatorWithButtons(
    pagerState: PagerState,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Previous Button (only visible if not on the first page)
        if (pagerState.currentPage > 0) {
            OutlinedButton(onClick = onPrevious) {
                Text("Previous")
            }
        } else {
            Spacer(modifier = Modifier.size(0.dp)) // Maintain layout consistency
        }

        // Next / Finish Button
        OutlinedButton(onClick = onNext) {
            Text(if (pagerState.currentPage == onboardingPage.size - 1) "Finish" else "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}