package se.daresay.domain.model

import java.lang.Exception

data class ParkingSpot(
    val id: Int,
    val name: String,
    val area: Area,
    val description: String,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double
)

enum class Area {
    KISTA,
    SOLNA,
}

fun toArea(str: String): Area =
    Area.valueOf(str)