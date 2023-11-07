package se.daresay.app.screen.car

import androidx.car.app.connection.CarConnection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import se.daresay.app.R
import se.daresay.app.ui.theme.Pink80
import se.daresay.app.ui.theme.Purple80

@Composable
fun CarConnectionScreenCompose(carConnectionType: Int?){
    val text = when (carConnectionType) {
        CarConnection.CONNECTION_TYPE_NATIVE -> "Connected to Android Automotive OS"
        CarConnection.CONNECTION_TYPE_PROJECTION -> "Connected to Android Auto"
        else -> "Knightec Parking needs to be connected to your car"
    }
    Column(
        modifier = Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Purple80,
                    Pink80
                )
            )
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = painterResource(id =
                if (carConnectionType == CarConnection.CONNECTION_TYPE_NATIVE ||
                    carConnectionType == CarConnection.CONNECTION_TYPE_PROJECTION)
                R.drawable.icon_car
                else
                R.drawable.icon_connection
            ),
            contentDescription = "car connection",
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}