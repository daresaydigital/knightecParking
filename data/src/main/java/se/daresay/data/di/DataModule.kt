package se.daresay.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.daresay.data.repoImp.ParkingRepositoryImp
import se.daresay.data.service.ParkingAPI
import se.daresay.domain.repo.ParkingRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Provides
    fun provideAPI(): ParkingAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/sasssass/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ParkingAPI::class.java)
    }

    @Binds
    abstract fun bindParkingRepo(parkingRepositoryImp: ParkingRepositoryImp): ParkingRepository
}