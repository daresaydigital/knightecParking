package se.daresay.car_service.session

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.login.SignInScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import kotlin.reflect.KClass

class MainSession(private val screenType: KClass<out BaseScreen>) : Session() {
    override fun onCreateScreen(intent: Intent): Screen {

        if (screenType == SignInScreen::class)
            return SignInScreen(carContext)
        else
            return ParkingOfficeListScreen(carContext)
    }
}
