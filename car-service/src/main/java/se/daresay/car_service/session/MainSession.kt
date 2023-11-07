package se.daresay.car_service.session

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import se.daresay.car_service.di.editorModule
import se.daresay.car_service.di.viewModelModule
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.login.SignInScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.data.di.apiModule
import se.daresay.data.di.repoModule
import se.daresay.data.di.usecaseModule
import kotlin.reflect.KClass

class MainSession(val screenType: KClass<out BaseScreen>) : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        stopKoin()
        GlobalContext.startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainSession.carContext)
            // Load modules
            modules(editorModule, apiModule, repoModule, viewModelModule, usecaseModule)
        }


        if (screenType == SignInScreen::class)
            return SignInScreen(carContext)
        else
            return ParkingOfficeListScreen(carContext)
    }
}
