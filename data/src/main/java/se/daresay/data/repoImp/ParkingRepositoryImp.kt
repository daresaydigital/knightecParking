package se.daresay.data.repoImp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import se.daresay.data.base.apiCallDomain
import se.daresay.data.service.ParkingAPI
import se.daresay.data.storage.PersistenceStorage
import se.daresay.domain.base.Response
import se.daresay.domain.model.Office
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository

class ParkingRepositoryImp constructor(
    private val parkingAPI: ParkingAPI,
    private val storage: PersistenceStorage
): ParkingRepository {

    override fun getAllParking(): Flow<Response<List<ParkingSpot>>> =
        apiCallDomain("loading") {
            val response = parkingAPI.getAllParking()
            withContext(Dispatchers.IO) {
                storage.savePots(response.toDomain())
            }
            response
        }

    override fun getAllAreas(): Flow<Response<List<Office>>> =
        apiCallDomain("loading area"){
            parkingAPI.getAllAreas()
        }

    override fun getSpotDetails(spotId: Int): Flow<ParkingSpot?> {
        return storage.getSpotDetails(spotId)
    }

    override suspend fun setFavorite(spot: ParkingSpot) {
        storage.setFavorite(spot)
    }

    override fun getFavorites(): Flow<List<ParkingSpot>> {
        return storage.getFavorites()
    }
}
