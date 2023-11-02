package se.daresay.car_service.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.db.load
import se.daresay.car_service.screen.login.SignInPasswordScreen
import se.daresay.car_service.screen.login.SignInUserNameScreen

class SplashScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {

        val token = carContext.load(TOKEN)

        if (token == null){
            screenManager.push(SignInUserNameScreen(carContext))
            screenManager.remove(this)
        }

        lifecycleScope.launch {
            delay(3000)
            // TODO go to GRID
        }

        val row = Row.Builder()
            .setTitle("Splash Screen")
            .build()
        val pane = Pane.Builder()
            .addRow(row)
            .build()
        return PaneTemplate.Builder(pane)
            .setTitle("Parking")
            .build()
    }
}