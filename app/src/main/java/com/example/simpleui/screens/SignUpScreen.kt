package com.example.simpleui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.simpleui.R
import com.example.simpleui.navigation.AppRoutes
import com.example.simpleui.toast.PopUpMessage
import com.example.simpleui.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {

    rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.WhiteSmoke))
            .padding(start = 10.dp, end = 10.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
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

        if (userViewModel.signUpMessage.isNotEmpty()) {
            PopUpMessage(
                message = userViewModel.signUpMessage,
                isSuccess = userViewModel.isSuccess
            )
        }

        OutlinedTextField(
            value = userViewModel.userName,
            onValueChange = {
                userViewModel.onNameChange(it)
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
            value = userViewModel.email,
            onValueChange = {
                userViewModel.onEmailChange(it)
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
            value = userViewModel.password,
            onValueChange = {
                userViewModel.onPasswordChange(it)
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
            value = userViewModel.confirmPassword,
            onValueChange = {
                userViewModel.onConfirmPasswordChange(it)
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
                userViewModel.addUser(navController)
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


        Spacer(Modifier.height(20.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(Modifier.weight(1f))
            Text(
                text = "Or sign up with?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
            HorizontalDivider(Modifier.weight(1f))
        }


        Spacer(modifier = Modifier.height(20.dp))
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