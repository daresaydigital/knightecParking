package se.daresay.car_service.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import se.daresay.car_service.screen.login.SignInViewModel

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
}