package se.daresay.car_service.session

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import se.daresay.car_service.screen.EmptyScreen

class MainSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return EmptyScreen(carContext)
    }
}