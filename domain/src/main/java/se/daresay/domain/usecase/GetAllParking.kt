package se.daresay.domain.usecase

import se.daresay.domain.repo.ParkingRepository

class GetAllParking constructor(
    private val repository: ParkingRepository
) {
    operator fun invoke() = repository.getAllParking()

}