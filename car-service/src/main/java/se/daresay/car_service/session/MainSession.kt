package se.daresay.car_service.session

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import se.daresay.car_service.screen.SplashScreen
import se.daresay.car_service.screen.login.SignInUserNameScreen
import kotlin.reflect.KClass

class MainSession(val screenType: KClass<Screen>) : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
    }
}