package se.daresay.data.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.daresay.data.modelDto.LoginDto
import se.daresay.data.modelDto.UserDto
import se.daresay.data.service.LoginAPI
import se.daresay.data.service.ParkingAPI
import se.daresay.data.storage.AppDatabase


val apiModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://gist.githubusercontent.com/sasssass/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


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

    fun provideParkingAPI(retrofit: Retrofit): ParkingAPI =
        retrofit.create(ParkingAPI::class.java)

    single { provideParkingAPI(get()) }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "database-name"
        ).build()
    }

    single {
        get<AppDatabase>().spotsDao()
    }
}


