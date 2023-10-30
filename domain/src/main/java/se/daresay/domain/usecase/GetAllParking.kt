package se.daresay.domain.usecase

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.base.BaseUseCase
import se.daresay.domain.base.Response
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.repo.ParkingRepository
import javax.inject.Inject

class GetAllParking @Inject constructor(
    private val repository: ParkingRepository
): BaseUseCase<Nothing, List<ParkingSpot>>() {
    override fun invoke(input: Nothing): Flow<Response<List<ParkingSpot>>> =
        repository.getAllParking()
}