package se.daresay.car_service.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import se.daresay.car_service.db.Editor

val editorModule = module {
    single {
        Editor(androidContext())
    }
}