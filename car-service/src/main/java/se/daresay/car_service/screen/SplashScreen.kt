package se.daresay.car_service.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.CarIcon
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.daresay.car_service.R
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.db.load
import se.daresay.car_service.screen.login.SignInPasswordScreen
import se.daresay.car_service.screen.login.SignInUserNameScreen
import se.daresay.car_service.screen.parkings.ParkingAreaListScreen
import se.daresay.car_service.screen.parkings.ParkingSpotsScreen

class SplashScreen(carContext: CarContext) : BaseScreen(carContext) {


    override fun onGetTemplate(): Template {

        val token = carContext.load(TOKEN)

        if (token == null){
            screenManager.push(SignInUserNameScreen(carContext))
            screenManager.remove(this)
        }

        lifecycleScope.launch {
            delay(3000)
            screenManager.push(ParkingAreaListScreen(carContext))
            screenManager.remove(this@SplashScreen)
        }
        val row = Row.Builder()
            .setTitle("Splash Screen")
            .setImage(CarIcon.Builder(IconCompat.createWithResource(carContext, R.drawable.icon_parking)).build())
            .build()
        val pane = Pane.Builder()
            .addRow(row)
            .build()
        return PaneTemplate.Builder(pane)
            .setTitle("Parking")
            .build()
    }
}