package com.example.weatherappCleanArchitecture

import com.example.presentation.screens.SearchScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappCleanArchitecture.ui.theme.WeatherAppcleanArchitectureTheme
import com.example.presentation.viewmodels.ViewModelfactory
import com.example.presentation.viewmodels.WeatherViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactoryProvider: ViewModelfactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as BaseApplication).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            WeatherAppcleanArchitectureTheme {
                val weatherViewModel: WeatherViewModel =
                    ViewModelProvider(
                        LocalContext.current as ComponentActivity,
                        viewModelFactoryProvider)[WeatherViewModel::class.java]
                SearchScreen(weatherViewModel)
            }
        }
    }
}
