package com.example.individualproject2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.individualproject2.data.AppPreference

@Composable
fun Login(navController: NavController) {
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferenceFlow.collectAsState(AppPreference())
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var password by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        Modifier.fillMaxSize()
            .background(Color(0xFFCCC2DC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(
                modifier = Modifier
                    .padding(10.dp),

                value = text,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { it ->
                    text = it
                }
            )
        }


        item {
            TextField(
                modifier = Modifier
                    .padding(10.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        item{
            Button(onClick = { navController.navigate("rules_page") }) {
                Text("Login")
            }
        }
    }
}