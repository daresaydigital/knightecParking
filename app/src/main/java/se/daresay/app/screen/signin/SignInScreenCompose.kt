package se.daresay.app.screen.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import se.daresay.app.Node
import se.daresay.app.navigate
import se.daresay.app.ui.theme.Pink80
import se.daresay.app.ui.theme.Purple80
import se.daresay.car_service.db.Editor.save
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.screen.login.SignInViewModel
import se.daresay.domain.model.User

@Composable
fun SignInScreenCompose(
    navController: NavController,
    viewModel: SignInViewModel
) {
    val user = viewModel.userInState.collectAsState().value
    val error = viewModel.loginState.collectAsState().value?.message?:""
    viewModel.loginState.collectAsState().value?.let {
        it.token?.let {
            save(TOKEN, it)
            navController.navigate(Node.CarConnection)
        }
    }
    SignInScreenCompose(
        viewModel::updateUsername,
        viewModel::updatePassword,
        viewModel::login,
        error,
        user
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreenCompose(
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignIn: () -> Unit,
    error: String,
    user: User,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
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

        Text(
            text = "Parking App",
            style = MaterialTheme.typography.headlineMedium,
        )

        TextField(
            value = user.username,
            onValueChange = { onUsernameChanged(it) },
            label = { Text(text = "Username") },
            singleLine = true,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = user.password,
            onValueChange = { onPasswordChanged(it) },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = { onSignIn() },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Sign In")
        }

        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview
@Composable
fun PreviewSignInScreenCompose() {
    SignInScreenCompose(
        {""},{""},{},"Error",User("Ali", "1")
    )
}