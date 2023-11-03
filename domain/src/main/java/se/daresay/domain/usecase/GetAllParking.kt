package se.daresay.domain.usecase

import se.daresay.domain.repo.ParkingRepository

class GetAllParking(
    private val repository: ParkingRepository
) {
    operator fun invoke() = repository.getAllParking()

}
