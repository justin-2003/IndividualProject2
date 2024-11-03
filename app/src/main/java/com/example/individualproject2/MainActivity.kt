package com.example.individualproject2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import com.example.individualproject2.data.AppPreference
import com.example.individualproject2.ui.theme.IndividualProject2Theme
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.io.PrintWriter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndividualProject2Theme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().background(
                        Color(0)
                    )
                ) {
                    Navigation()
                }
            }
        }
        writeToInternalFile()
        val fileContents = readFromInternalFile()
        Log.d("MainActivity", fileContents)
    }
    private fun writeToInternalFile() {
        val outputStream: FileOutputStream = openFileOutput("fav_haiku", Context.MODE_PRIVATE)
        val writer = PrintWriter(outputStream)

        // Write three lines
        writer.println("This world of dew")
        writer.println("is a world of dew,")
        writer.println("and yet, and yet.")

        writer.close()
    }

    private fun readFromInternalFile(): String {
        val inputStream = openFileInput("fav_haiku")
        val reader = inputStream.bufferedReader()
        val stringBuilder = StringBuilder()

        // Append each line and newline character to stringBuilder
        reader.forEachLine {
            stringBuilder.append(it).append("\n BCS 371 \n").append(System.lineSeparator())
        }

        return stringBuilder.toString()
    }
}
