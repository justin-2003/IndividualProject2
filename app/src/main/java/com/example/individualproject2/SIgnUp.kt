package com.example.individualproject2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.individualproject2.data.AppPreference
import kotlinx.coroutines.launch


@Composable
fun SignUp(navController: NavController) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var dob by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf("") }
    val store = AppStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()


    LazyColumn(
        Modifier.fillMaxSize()
        .background(Color(0xFFCCC2DC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = firstName,
                onValueChange = { input ->
                    firstName = input
                },
                label = { Text("First Name")},
                isError = firstName.text.length in 3..30 && !First_LastName(firstName.text)
            )
        }

        item{
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp),
                value = lastName,
                onValueChange = { input ->
                    lastName = input
                },
                label = { Text("Last Name") },
                isError = lastName.text.length in 3..30 && !First_LastName(lastName.text)
            )
        }

        item{
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp),
                value = dob,
                onValueChange = { input ->
                    dob= input
                },
                label = { Text("DOB") },
                isError = dob.text.isNotEmpty() && !isValidDob(dob.text)
            )
        }

        item{
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp),
                value = email,
                onValueChange = { input ->
                    email= input
                },
                label = { Text("Email") },
                isError = email.text.isNotEmpty() && !emailIsValid(email.text)
            )
        }

        item{
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp),
                value = password ,
                onValueChange = { input ->
                    password  = input
                },
                label = { Text("Password") },
                isError = password .isNotEmpty() && !isValidPassword(password )
            )
        }

        item{
            Button(onClick = { navController.navigate("first_screen")
                coroutineScope.launch {
                    store.saveUsername(email.toString())
                    store.savePassword(password)
                }
            }) {
                Text("SignUp")
            }
        }
    }
}

fun First_LastName(text: String): Boolean {
    return text.matches(Regex("[a-zA-Z]+"))
}
fun emailIsValid(text: String): Boolean {
    return text.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
}
fun isValidDob(text: String): Boolean {
    return text.matches(Regex("(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d{2})"))
}
fun isValidPassword(text: String): Boolean{
    return text.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"))
}