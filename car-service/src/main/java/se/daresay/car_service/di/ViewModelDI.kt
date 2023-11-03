package se.daresay.car_service.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import se.daresay.car_service.screen.details.DetailsViewModel
import se.daresay.car_service.screen.favorite.FavoritesViewModel
import se.daresay.car_service.screen.login.SignInViewModel
import se.daresay.car_service.screen.parkings.ParkingViewModel

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { ParkingViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
}
