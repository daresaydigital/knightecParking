package se.daresay.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.daresay.data.service.LoginAPI
import se.daresay.data.service.ParkingAPI

val apiModule = module {

    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/sasssass/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    single { provideRetrofit() }

    fun provideLoginAPI(retrofit: Retrofit) : LoginAPI =
        retrofit.create(LoginAPI::class.java)

    single { provideLoginAPI(get()) }

    fun provideParkingAPI(retrofit: Retrofit) : ParkingAPI =
        retrofit.create(ParkingAPI::class.java)

    single { provideParkingAPI(get()) }

}


