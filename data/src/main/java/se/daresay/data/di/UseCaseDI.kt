package se.daresay.data.di

import org.koin.dsl.module
import se.daresay.domain.usecase.GetAllFavoriteSpots
import se.daresay.domain.usecase.GetAllFavoriteSpotsImpl
import se.daresay.domain.usecase.GetAllOffices
import se.daresay.domain.usecase.GetAllParking
import se.daresay.domain.usecase.GetSpotDetails
import se.daresay.domain.usecase.GetSpotDetailsImpl
import se.daresay.domain.usecase.LogIn
import se.daresay.domain.usecase.SetFavorite
import se.daresay.domain.usecase.SetFavoriteImpl

val usecaseModule = module {
    single{
        LogIn(get())
    }

    single{
        GetAllParking(get())
    }

    single {
        GetAllOffices(get())
    }

    factory<GetSpotDetails> { GetSpotDetailsImpl(get()) }
    factory<SetFavorite> { SetFavoriteImpl(get()) }
    factory<GetAllFavoriteSpots> { GetAllFavoriteSpotsImpl(get()) }
}
