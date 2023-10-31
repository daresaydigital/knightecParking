package se.daresay.app.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
//    val text = when (carConnectionType) {
//        CarConnection.CONNECTION_TYPE_NOT_CONNECTED -> "Not projecting"
//        CarConnection.CONNECTION_TYPE_NATIVE -> "Running on Android Automotive OS"
//        CarConnection.CONNECTION_TYPE_PROJECTION -> "Projecting"
//        else -> "Unknown connection type"
//    }

    // TODO Show Car Connection here
    Text(
        text = "Show Car Connection here",
        style = MaterialTheme.typography.bodyMedium,
    )
}