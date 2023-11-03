package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.db.load
import se.daresay.car_service.screen.login.SignInUserNameScreen
import se.daresay.car_service.screen.parkings.ParkingAreaListScreen
import se.daresay.car_service.session.MainSession

class CarService: CarAppService() {
    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session {
        val token = load(TOKEN)
        if (token == null){
            MainSession()
            screenManager.push(SignInUserNameScreen(carContext))
            screenManager.remove(this@SplashScreen)
        } else {
            screenManager.push(ParkingAreaListScreen(carContext))
            screenManager.remove(this@SplashScreen)
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
