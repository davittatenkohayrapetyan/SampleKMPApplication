package com.davithayrapetyan.samplekmpapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davithayrapetyan.samplekmpapplication.SpaceXApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingScreen()
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Composable
fun GreetingScreen() {
    var greetingText by remember { mutableStateOf("Loading...") }
    val spaceXApi = SpaceXApi()
    val coroutineScope = rememberCoroutineScope()

    // Layout with button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        GreetingView(greetingText)

        Spacer(modifier = Modifier.height(16.dp))

        // Add a button
        Button(onClick = {
            // Perform API call in coroutine
            coroutineScope.launch {
                greetingText = "Button Clicked! Fetching new data..."
                val newResult = spaceXApi.fetchNextLaunch()
                greetingText = newResult
            }
        }) {
            Text("Refresh Data")
        }
    }
}
