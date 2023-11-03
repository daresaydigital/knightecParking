package se.daresay.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import se.daresay.data.base.ToDomain
import se.daresay.domain.model.Area
import se.daresay.domain.model.ParkingSpot

@Entity
data class ParkingSpotEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val area: Area,
    val description: String,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
) : ToDomain<ParkingSpot> {
    override fun toDomain(): ParkingSpot {
        return ParkingSpot(
            id = id,
            name = name,
            area = area,
            description = description,
            isActive = isActive,
            latitude = latitude,
            longitude = longitude,
            isFavorite = isFavorite
        )
    }
}


fun ParkingSpot.toEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        id = id,
        name = name,
        area = area,
        description = description,
        isActive = isActive,
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite
    )
}
