package se.daresay.data.di

import org.koin.dsl.module
import se.daresay.domain.usecase.GetAllArea
import se.daresay.domain.usecase.GetAllParking
import se.daresay.domain.usecase.LogIn

val usecaseModule = module {
    single{
        LogIn(get())
    }

    single{
        GetAllParking(get())
    }

    single {
        GetAllArea(get())
    }
}