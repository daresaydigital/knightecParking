package se.daresay.car_service.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import se.daresay.car_service.screen.login.SignInViewModel
import se.daresay.car_service.screen.parkings.ParkingSpotsViewModel

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { ParkingSpotsViewModel(get()) }
}