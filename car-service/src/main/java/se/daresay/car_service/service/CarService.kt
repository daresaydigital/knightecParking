package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import se.daresay.car_service.db.Editor
import se.daresay.car_service.db.Editor.load
import se.daresay.car_service.db.Editor.loadValue
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.screen.login.SignInScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.car_service.session.MainSession

class CarService: CarAppService() {
    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session {
        val token = loadValue(TOKEN)
        val clazz = if (token == null){
            SignInScreen::class
        } else {
            ParkingOfficeListScreen::class
        }
        return MainSession(clazz)
    }

    override fun onCreate() {
        super.onCreate()
        Editor.build(this)
    }
}
