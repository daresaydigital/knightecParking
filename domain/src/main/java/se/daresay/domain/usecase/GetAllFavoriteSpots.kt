package se.daresay.domain.usecase

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository

interface GetAllFavoriteSpots {

    operator fun invoke(): Flow<List<ParkingSpot>>
}

class GetAllFavoriteSpotsImpl(private val repository: ParkingRepository) : GetAllFavoriteSpots {

    override fun invoke(): Flow<List<ParkingSpot>> {
        return repository.getFavorites()
    }

}
