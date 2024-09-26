package com.davithayrapetyan.samplekmpapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.davithayrapetyan.samplekmpapplication.SpaceXApi
import kotlinx.coroutines.MainScope

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

    LaunchedEffect(Unit) {
        val result = spaceXApi.fetchNextLaunch()
        greetingText = result
    }

    GreetingView(greetingText)
}
