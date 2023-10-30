package se.daresay.data.modelDto

import se.daresay.data.base.ToDomain
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.model.toArea

data class ParkingSpotDtoResponse(
    val response: List<ParkingSpotDto>
): ToDomain<List<ParkingSpot>>{
    override fun toDomain(): List<ParkingSpot> {
        return response.map {
            with(it){
                ParkingSpot(
                    id = id,
                    name = name,
                    area = toArea(area),
                    description = description,
                    isActive = isActive,
                    latitude = latitude,
                    longitude = longitude
                )
            }

        }
    }
}

data class ParkingSpotDto(
    val id: Int,
    val name: String,
    val area: String,
    val description: String,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double
): ToDomain<ParkingSpot>{
    override fun toDomain(): ParkingSpot =
        ParkingSpot(
            id = id,
            name = name,
            area = toArea(area),
            description = description,
            isActive = isActive,
            latitude = latitude,
            longitude = longitude
        )
}