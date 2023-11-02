package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import se.daresay.car_service.session.MainSession

class CarService: CarAppService() {
    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session =
        MainSession()

//    override fun onCreate() {
//        super.onCreate()
//        GlobalContext.startKoin {
//            // Log Koin into Android logger
//            androidLogger()
//            // Reference Android context
//            androidContext(this@CarService)
//            // Load modules
//            modules(dataModule)
//        }
//    }
}
