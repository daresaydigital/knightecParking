package se.daresay.data.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import se.daresay.data.storage.model.ParkingSpotStatusEntity
import se.daresay.data.storage.model.toEntity
import se.daresay.domain.model.ParkingSpot

interface PersistenceStorage {

    suspend fun savePots(spots: List<ParkingSpot>)

    fun getSpotDetails(sportId: Int): Flow<ParkingSpot?>

    suspend fun setFavorite(spot: ParkingSpot)

    fun getFavorites(): Flow<List<ParkingSpot>>
}


class PersistenceStorageImpl(
    private val spotDao: SpotDao
) : PersistenceStorage {

    override suspend fun savePots(spots: List<ParkingSpot>) {
        spotDao.insertAll(spots.map { it.toEntity() })
    }

    override fun getSpotDetails(sportId: Int): Flow<ParkingSpot?> {
        return spotDao.getSpotWithStatus(sportId).map { it.parkingSpot.toDomain().copy(isFavorite = it.spotStatus?.isFavorite ?: false) }
    }

    override suspend fun setFavorite(spot: ParkingSpot) {
        spotDao.insertSpotStatus(ParkingSpotStatusEntity(id = spot.id, isFavorite = spot.isFavorite))
    }

    override fun getFavorites(): Flow<List<ParkingSpot>> {
        return spotDao.getFavorites().map { it.map { it.parkingSpot.toDomain().copy(isFavorite = it.spotStatus?.isFavorite ?: false) }  }
    }
}
