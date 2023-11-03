package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.db.load
import se.daresay.car_service.screen.login.SignInScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.car_service.session.MainSession

class CarService: CarAppService() {
    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session {
        val token = load(TOKEN)
        val clazz = if (token == null){
            SignInScreen::class
        } else {
            ParkingOfficeListScreen::class
        }
        return MainSession(clazz)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
