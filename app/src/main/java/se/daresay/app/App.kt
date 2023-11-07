package se.daresay.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import se.daresay.car_service.db.Editor
import se.daresay.car_service.di.editorModule
import se.daresay.car_service.di.viewModelModule
import se.daresay.data.di.apiModule
import se.daresay.data.di.repoModule
import se.daresay.data.di.usecaseModule

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        stopKoin()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(editorModule, apiModule, repoModule, viewModelModule, usecaseModule)
        }

    }
}
