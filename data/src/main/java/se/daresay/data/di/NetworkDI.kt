package se.daresay.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.daresay.data.modelDto.LoginDto
import se.daresay.data.modelDto.UserDto
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
        object : LoginAPI{
            override suspend fun logIn(user: UserDto): LoginDto {
                if (user.username == "Ali" && user.password.hashCode() == 49)
                    return LoginDto("12345", "you're logged in")
                else
                    return LoginDto(null, "wrong username or password")
            }

        }

    single { provideLoginAPI(get()) }

    fun provideParkingAPI(retrofit: Retrofit) : ParkingAPI =
        retrofit.create(ParkingAPI::class.java)

    single { provideParkingAPI(get()) }

}


