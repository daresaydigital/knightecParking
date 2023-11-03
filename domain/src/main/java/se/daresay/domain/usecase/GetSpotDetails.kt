package se.daresay.domain.usecase

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository

interface GetSpotDetails {
    suspend operator fun invoke(spotId: Int): Flow<ParkingSpot?>
}

class GetSpotDetailsImpl(private val repository: ParkingRepository) : GetSpotDetails {

    override suspend fun invoke(spotId: Int): Flow<ParkingSpot?> {
        return repository.getSpotDetails(spotId);
    }
}
