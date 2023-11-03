package se.daresay.domain.usecase

import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository

interface SetFavorite {

    suspend operator fun invoke(spot: ParkingSpot)
}

class  SetFavoriteImpl(private val repository: ParkingRepository): SetFavorite {
    override suspend fun invoke(spot: ParkingSpot) {
        repository.setFavorite(spot)
    }
}
