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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.simpleui.navigation.AppRoutes
import com.example.simpleui.R
import com.example.simpleui.toast.PopUpMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
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

    val coroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.WhiteSmoke))
    ) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp, top = 24.dp)
                .align(Alignment.TopStart)
                .zIndex(1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Login",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
            )

            Text(
                text = "Welcome Back.",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Sign in to your account.",
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (showMessage) {
                PopUpMessage(message = message, isSuccess = isSuccess)
            }

            OutlinedTextField(
                value = email, onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = {
                    Text(
                        "Email Address",
                        fontSize = 20.sp
                    )
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black
                )
            )

            OutlinedTextField(
                value = password, onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = {
                    Text(
                        "Password",
                        fontSize = 20.sp

                    )
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        message = "Login Successful"
                        coroutineScope.launch {
                            loginCheck(navController,email)
                        }
                        isSuccess = true
                    } else {
                        message = "Please fill all fields!"
                    }
                    showMessage = true
                },

                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.DeepOrange)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(56.dp)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = {
                if (email.isNotEmpty()) {
                    navController.navigate(AppRoutes.ForgotPasswordScreen.route + "/$email")
                } else {
                    navController.navigate(AppRoutes.ForgotPasswordScreen.route + "/")
                }
            }) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Or sign in with",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                Image(
                    painter = painterResource(R.drawable.faceboook),
                    contentDescription = "Facebook Login",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .clickable {
                            TODO("SOCIAL LOGIN")
                        }
                )
                Image(
                    painter = painterResource(R.drawable.google), contentDescription = "Google Login",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .clickable {
                            TODO("SOCIAL LOGIN")
                        }
                )
                Image(
                    painter = painterResource(R.drawable.twitter), contentDescription = "Twitter Login",
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

suspend fun loginCheck(navController: NavController, email: String){
    delay(1000)
    navController.navigate(AppRoutes.HomeScreen.route + "/$email")
}
