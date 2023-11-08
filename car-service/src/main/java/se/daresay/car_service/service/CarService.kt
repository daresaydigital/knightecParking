package se.daresay.car_service.service

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.db.Editor
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.di.editorModule
import se.daresay.car_service.di.viewModelModule
import se.daresay.car_service.screen.login.SignInScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.car_service.session.MainSession
import se.daresay.data.di.apiModule
import se.daresay.data.di.repoModule
import se.daresay.data.di.usecaseModule

class CarService: CarAppService() {

    private lateinit var editor: Editor

    init {
        stopKoin()
        GlobalContext.startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@CarService)
            // Load modules
            modules(editorModule, apiModule, repoModule, viewModelModule, usecaseModule)
        }
    }

    override fun createHostValidator(): HostValidator =
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session {
        val token = editor.loadValue(TOKEN)
        val clazz = if (token == null){
            SignInScreen::class
        } else {
            ParkingOfficeListScreen::class
        }
        return MainSession(clazz)
    }

    override fun onCreate() {
        super.onCreate()
        editor = get(Editor::class.java)
    }
}
