package com.example.simpleui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {

    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var showMessage by remember {
        mutableStateOf(false)
    }
    var isSuccess by remember {
        mutableStateOf(false)
    }
    var message by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.stars), contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.8f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.signup), contentDescription = "Sign Up image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
            )
            Text(
                text = "Welcome.",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                text = "Sign up to continue.",
                fontSize = 12.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (showMessage) {
                PopUpMessage(message = message, isSuccess = isSuccess)
            }

            OutlinedTextField(
                value = username, onValueChange = {
                    username = it
                },
                label = {
                    Text(
                        "Username",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = email, onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        "Email Address",
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = password, onValueChange = {
                    password = it
                },
                label = {
                    Text(
                        "Password",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                ), visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = confirmPassword, onValueChange = {
                    confirmPassword = it
                },
                label = {
                    Text(
                        "Confirm Password",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                ), visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedButton(
                onClick = {
                    if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        message = "Please fill all fields!"
                        isSuccess = false
                    } else {
                        message = "Account created successfully!"
                        isSuccess = true
                        navController.navigate(AppRoutes.HomeScreen.route)
                    }
                    showMessage = true
                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(56.dp)
            ) {
                Text(
                    "Sign Up",
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Login",
                    modifier = Modifier.clickable {
                        navController.navigate(AppRoutes.LoginScreen.route)
                    },
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    color = Color.Cyan,
                    textDecoration = TextDecoration.Underline
                )
            }

            Text(
                text = "Or sign up with?",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google Sign Up",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .clickable {
                            TODO("SOCIAL LOGIN")
                        }
                )

                Image(
                    painter = painterResource(R.drawable.faceboook),
                    contentDescription = "Facebook Sign Up",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .clickable {
                            TODO("SOCIAL LOGIN")
                        }
                )

                Image(
                    painter = painterResource(R.drawable.twitter),
                    contentDescription = "Twitter Sign Up",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .clickable {
                            TODO("SOCIAL LOGIN")
                        }
                )
            }
        }
    }
}
