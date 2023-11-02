package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import se.daresay.car_service.session.MainSession

class CarService: CarAppService() {
    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session =
        MainSession()

    override fun onCreate() {
        super.onCreate()
    }
}
