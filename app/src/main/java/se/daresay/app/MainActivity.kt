package se.daresay.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.car.app.connection.CarConnection
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.app.screen.car.CarConnectionScreenCompose
import se.daresay.app.screen.signin.SignInScreenCompose
import se.daresay.app.ui.theme.ParkingTheme
import se.daresay.car_service.db.Editor.load
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.screen.login.SignInViewModel
import se.daresay.app.navigate
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val carConnectionType = CarConnection(this).type.observeAsState().value
            ParkingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    var token: String? = null
                    LaunchedEffect(true){
                        lifecycleScope.launch {
                            load(TOKEN).collect {
                                it?.let {
                                    if (it != "null"){
                                        token = it
                                        navController.navigate(Node.CarConnection.route)
                                    }
                                }

                            }
                        }
                    }

                    val startDes =
                        if (token == null)
                            Node.SignIn.route
                        else
                            Node.CarConnection.route
                    NavHost(navController = navController, startDestination = startDes) {
                        composable(Node.SignIn.route) {
                            SignInScreenCompose(navController, get(SignInViewModel::class.java))
                        }
                        composable(Node.CarConnection.route) {
                            CarConnectionScreenCompose(carConnectionType)
                        }
                    }


                }
            }
        }
    }
}

