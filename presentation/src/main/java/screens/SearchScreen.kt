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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.viewmodels.WeatherViewModel


@Composable
fun SearchScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar()
        SearchCity()
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Blue)
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
fun SearchCity(modifier: Modifier = Modifier) {


    Spacer(modifier.padding(top = 40.dp))

    var cityName by remember { mutableStateOf("") }

    // State to manage password visibility
    // var passwordVisible by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = cityName,
            label = { Text("Enter City") },
            placeholder = { Text("City") },
            onValueChange = { cityName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        OutlinedButton(
            border = BorderStroke(3.dp, Color.Gray),
            onClick = {},
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = outlinedButtonColors(contentColor = Color.Gray),
        ) {
            Text(
                text = "Search   ",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}


