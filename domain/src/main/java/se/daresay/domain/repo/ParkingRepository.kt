package se.daresay.domain.repo

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.base.Response
import se.daresay.domain.model.Office
import se.daresay.domain.model.ParkingSpot

interface ParkingRepository {
    fun getAllParking(): Flow<Response<List<ParkingSpot>>>

    fun getAllAreas(): Flow<Response<List<Office>>>

    fun getSpotDetails(spotId: Int): Flow<ParkingSpot?>

    suspend fun setFavorite(spot: ParkingSpot)

    fun getFavorites(): Flow<List<ParkingSpot>>
}

