package se.daresay.car_service.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.lifecycle.DefaultLifecycleObserver

abstract class BaseScreen(carContext: CarContext): Screen(carContext), DefaultLifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }
}