package se.daresay.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import se.daresay.data.storage.model.ParkingSpotEntity
import se.daresay.data.storage.model.ParkingSpotStatusEntity

@Database(entities = [ParkingSpotEntity::class, ParkingSpotStatusEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun spotsDao(): SpotDao
}
