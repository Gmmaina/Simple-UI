package com.example.simpleui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.simpleui.R
import com.example.simpleui.navigation.AppRoutes
import com.example.simpleui.toast.PopUpMessage

@Composable
fun SignUpScreen(navController: NavHostController) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.WhiteSmoke))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Column {
                Text(
                    text = "Welcome.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Sign up to continue.",
                    fontSize = 12.sp,
                )
            }
        }


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
                )
            },
            textStyle = TextStyle(
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
                    fontSize = 20.sp
                )
            },
            textStyle = TextStyle(
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
                )
            },
            textStyle = TextStyle(
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
                )
            },
            textStyle = TextStyle(
                fontSize = 24.sp
            ), visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    message = "Please fill all fields!"
                    isSuccess = false
                } else {
                    message = "Account created successfully!"
                    isSuccess = true
                    navController.navigate(AppRoutes.HomeScreen.route + "/$email")
                }
                showMessage = true
            },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.DeepOrange)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(56.dp)
        ) {
            Text(
                "Sign Up",
                fontSize = 20.sp,
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
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Login",
                modifier = Modifier.clickable {
                    navController.navigate(AppRoutes.LoginScreen.route)
                },
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color.Magenta,
                textDecoration = TextDecoration.Underline
            )
        }

        Text(
            text = "Or sign up with?",
            fontSize = 16.sp,
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

@Preview
@Composable
private fun PreviewScreen() {
    SignUpScreen(navController = NavHostController(LocalContext.current))
}