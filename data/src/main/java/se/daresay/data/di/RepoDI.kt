package se.daresay.data.di

import org.koin.dsl.module
import se.daresay.data.repoImp.LoginRepositoryImp
import se.daresay.data.repoImp.ParkingRepositoryImp
import se.daresay.data.storage.PersistenceStorage
import se.daresay.data.storage.PersistenceStorageImpl
import se.daresay.domain.repo.LoginRepository
import se.daresay.domain.repo.ParkingRepository

val repoModule = module {
    single<LoginRepository> { LoginRepositoryImp(get()) }
    single<ParkingRepository> { ParkingRepositoryImp(get(), get()) }
    single<PersistenceStorage> { PersistenceStorageImpl(get()) }
}
