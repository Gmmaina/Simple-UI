package com.example.simpleui

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay

@Composable
fun LoginScreen() {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    var showError by remember {
        mutableStateOf(false)
    }

    if (showError){
        LaunchedEffect(Unit) {
            delay(2000)
            showError = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "Login",
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
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

       ErrorMessage(showError)

        OutlinedTextField(
            value = email, onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            label = { Text("Email Address") })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password, onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            label = {
                Text("Password")
            }, visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                showError = email.isEmpty() || password.isEmpty()
                if (email.isEmpty() || password.isEmpty()) {
                    Toasty.error(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    Log.i("Credentials", "Email: $email Password: $password")
                    Toasty.success(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                }
            },

            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text(
                text = "Sign In",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {
            TODO("ADD FORGOT PASSWORD BUTTON FUNCTIONALITY")
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

                    }
            )
        }
    }
}


@Composable
fun ErrorMessage(showError: Boolean){
    AnimatedVisibility(
        visible = showError,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
                .background(
                    color = Color.Red.copy(0.7f),
                ),

            ) {
            Text(
                text = "Please fill all the fields!",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}