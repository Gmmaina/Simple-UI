package com.example.simpleui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(userEmail: String?) {
    val email = userEmail ?: ""

    val languages = listOf("Kotlin", "Java", "React", "Android", "Python")

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(languages[0])
    }

    val selectedLanguage = remember {
        mutableStateOf(languages[0])
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val openDialog = remember {
        mutableStateOf(false)
    }
    val buttonColors =
        ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.LightGray)
    val scope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor = Color.LightGray
            ) {
                languages.forEach { language ->
                    NavigationDrawerItem(
                        label = { Text(language, fontSize = 22.sp) },
                        selected = selectedLanguage.value == language,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedLanguage.value = language
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.LightGray,
                            unselectedContainerColor = Color.Transparent,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.LightGray
                        )
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .windowInsetsPadding(WindowInsets.systemBars),
        ) {
            Row {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    content = {
                        Icon(
                            Icons.Filled.Menu, "Menu"
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    selectedLanguage.value,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Button(
                { openDialog.value = true },
                colors = buttonColors,
                border = BorderStroke(1.dp, color = Color.LightGray),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            ) {
                Text(
                    "Click Me",
                    fontSize = 22.sp
                )
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = { Text(text = "Payment Confirmation!") },
                    text = { Text("Send Money to.") },
                    confirmButton = {
                        Button(
                            { openDialog.value = false },
                            colors = buttonColors,
                            border = BorderStroke(1.dp, color = Color.LightGray)
                        ) {
                            Text(
                                text = "Confirm",
                                fontSize = 22.sp
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            { openDialog.value = false },
                            colors = buttonColors,
                            border = BorderStroke(1.dp, color = Color.LightGray)
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 22.sp
                            )
                        }
                    },
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    textContentColor = Color.LightGray,
                    iconContentColor = Color.LightGray
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello Buddy $email"
                )
                Column {
                    Text(
                        text = selectedOption
                    )
                    Column(modifier = Modifier.selectableGroup()) {
                        languages.forEach { text ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    }
                                )
                                Text(
                                    text = text,
                                )
                            }
                        }
                    }
                }

            }

            FloatingActionButton(
                onClick = {

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(userEmail = "test@test.com")
}
