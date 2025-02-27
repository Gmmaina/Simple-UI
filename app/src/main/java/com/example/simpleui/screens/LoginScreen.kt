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
import androidx.navigation.NavController
import com.example.simpleui.navigation.AppRoutes
import com.example.simpleui.R
import com.example.simpleui.toast.PopUpMessage


@Composable
fun LoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

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
            .background(colorResource(R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Icon",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(32.dp)

                    .clickable {
                        navController.popBackStack()
                    }
            )
        }

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
                .fillMaxWidth(0.8f)
                .height(56.dp),
            label = {
                Text("Email Address")
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password, onValueChange = {
                password = it
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            label = {
                Text("Password")
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    message = "Login Successful"
                    navController.navigate(AppRoutes.HomeScreen.route + "/$email")
                    isSuccess = true
                } else {
                    message = "Please fill all fields!"
                }
                showMessage = true
            },

            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6F22)
            ),
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Sign In",
                fontSize = 20.sp,
                color = Color.White
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
