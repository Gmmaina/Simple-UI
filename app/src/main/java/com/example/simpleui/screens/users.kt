package com.example.simpleui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpleui.data.User
import com.example.simpleui.viewmodel.UserViewModel

@Composable
fun User(vm: UserViewModel = viewModel()) {
    Column {
        UserData(vm.username, vm.age, updateName = { vm.updateUsername(it) }, updateAge = { vm.updateAge(it) },add = { vm.addUser() })
        UserList(vm.users, delete = { vm.removeUser(it) })
    }
}

@Preview
@Composable
fun UserPreview() {
    val vm: UserViewModel = viewModel()
    User(vm)
}

@Composable
fun UserList(users: List<User>, delete: (User) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(users) { user ->
            Text(text = user.name, modifier = Modifier.padding(8.dp), fontSize = 20.sp)
            Text(text = user.age.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
            Button(onClick = { delete(user) }, modifier = Modifier.padding(8.dp)) {
                Row{
                    Text("Delete", fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun UserData(userName: String, age: Int, updateName: (String) -> Unit, updateAge: (String) -> Unit, add: () -> Unit) {
    OutlinedTextField(
        value = userName,
        modifier = Modifier.padding(8.dp),
        onValueChange = { updateName(it) },
        label = { Text("Enter Name") }
    )
    OutlinedTextField(
        value = age.toString(),
        modifier = Modifier.padding(8.dp),
        onValueChange = { updateAge(it) },
        label = { Text("Enter Age")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Button(onClick = { add() }, modifier = Modifier.padding(8.dp)) {
        Text("Add User", fontSize = 20.sp)
    }

}