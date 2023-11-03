package se.daresay.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import se.daresay.data.storage.model.ParkingSpotEntity
import se.daresay.data.storage.model.ParkingSpotStatusEntity
import se.daresay.data.storage.model.ParkingSpotWithStatusEntity

@Dao
interface SpotDao {
    @Query("SELECT * FROM parkingspotentity WHERE id = :id")
    fun getSpotWithStatus(id: Int): Flow<ParkingSpotWithStatusEntity>

    @Query("SELECT * FROM parkingspotentity JOIN parkingspotstatusentity ON parkingspotentity.id = parkingspotstatusentity.id  WHERE parkingspotstatusentity.isFavorite")
    fun getFavorites(): Flow<List<ParkingSpotWithStatusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(spots: List<ParkingSpotEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpotStatus(spot: ParkingSpotStatusEntity)
}
