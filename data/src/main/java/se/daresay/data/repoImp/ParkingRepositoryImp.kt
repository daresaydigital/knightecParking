package se.daresay.data.repoImp

import kotlinx.coroutines.flow.Flow
import se.daresay.data.base.apiCallDomain
import se.daresay.data.service.ParkingAPI
import se.daresay.domain.base.Response
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository
import javax.inject.Inject

class ParkingRepositoryImp @Inject constructor(
    private val parkingAPI: ParkingAPI
): ParkingRepository {
    override  fun getAllParking(): Flow<Response<List<ParkingSpot>>> =
        apiCallDomain("loading"){
            parkingAPI.getAllParking()
        }
}