package com.example.simpleui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simpleui.data.User
import com.example.simpleui.navigation.BottomNavigationBar
import com.example.simpleui.viewmodel.UserViewModel

@Composable
fun AdminDashboard(navController: NavController, viewModel: UserViewModel) {

    val users = viewModel.getUsers().observeAsState(emptyList()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    )
    Scaffold(
        bottomBar = {BottomNavigationBar(navController)}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(users.size) { index ->
                UserList(users[index]) { userId ->
                    viewModel.deleteUser(userId)
                }

            }
        }
    }

}

@Composable
fun UserList(user: User, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = user.username, modifier = Modifier.padding(8.dp))
                Text(text = user.email, modifier = Modifier.padding(8.dp))
            }
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Filled.Delete,
                "Delete icon",
                tint = Color.Black,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onDelete(user.id)
                    }
            )
        }
    }
}