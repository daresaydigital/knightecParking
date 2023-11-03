package se.daresay.domain.usecase

import se.daresay.domain.repo.ParkingRepository

class GetAllArea(
    private val repository: ParkingRepository
) {
    operator fun invoke() = repository.getAllAreas()
}