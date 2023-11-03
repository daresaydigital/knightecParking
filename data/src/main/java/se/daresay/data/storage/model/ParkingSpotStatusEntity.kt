package se.daresay.data.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ParkingSpotStatusEntity(
    @PrimaryKey val id: Int,
    val isFavorite: Boolean = false,
)

data class ParkingSpotWithStatusEntity(
    @Embedded val parkingSpot: ParkingSpotEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val spotStatus: ParkingSpotStatusEntity?
)
