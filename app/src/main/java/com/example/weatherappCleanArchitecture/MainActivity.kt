package com.example.weatherappCleanArchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherappCleanArchitecture.ui.theme.WeatherApp_cleanArchitectureTheme

//import com.example.weatherapp_cleanarchitecture.ui.theme.WeatherApp_cleanArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp_cleanArchitectureTheme {

            }
        }
    }
}

