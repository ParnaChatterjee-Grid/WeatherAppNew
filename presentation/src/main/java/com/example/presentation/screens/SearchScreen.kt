package com.example.presentation.screens

import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.ResultState
import com.example.presentation.R
import com.example.presentation.themes.color
import com.example.presentation.themes.dimens
import com.example.presentation.viewmodels.WeatherViewModel


@Composable
fun SearchScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {

    Column(Modifier.fillMaxSize(1f)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.color.white)
        ) {
            TopBar()
            SearchCity(weatherViewModel, modifier)
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.app_bar_height)
            .background(MaterialTheme.color.topbar_box)
    )
    {
        Text(
            text = stringResource(R.string.weather_app),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

//For searching city
@Composable
fun SearchCity(weatherViewModel: WeatherViewModel, modifier: Modifier = Modifier) {

    Spacer(modifier.padding(top = MaterialTheme.dimens.largePadding))

    var cityName by rememberSaveable { mutableStateOf("") }
    var IsFetchedData by rememberSaveable { mutableStateOf(false) }
    val cityState = weatherViewModel.cityState.collectAsStateWithLifecycle()
    remember { cityState }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        //TextField for input the cityname
        OutlinedTextField(
            value = cityName,
            label = { Text("Enter City") },
            placeholder = { Text("City") },
            onValueChange = { cityName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.mediumPadding)
        )
        //Search Button
        OutlinedButton(
            border = BorderStroke(3.dp, Color.Gray),
            onClick = {
                weatherViewModel.getCity(cityName)
                IsFetchedData = true
            },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(MaterialTheme.dimens.smallPadding),
            shape = RoundedCornerShape(MaterialTheme.dimens.roundedCorner),
            colors = outlinedButtonColors(contentColor = Color.Gray),
        ) {
            Text(
                text = "Search",
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
        if (IsFetchedData) {
            when (val state = cityState.value) {
                is ResultState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                }

                is ResultState.Error -> {
                    state.exception.localizedMessage?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                is ResultState.Success -> {
                    //It will call the weather screen
                }
            }
        }
    }
}
