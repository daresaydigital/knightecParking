package se.daresay.domain.model

import android.content.Intent
import androidx.core.net.toUri
import java.lang.Exception

data class ParkingSpot(
    val id: Int,
    val name: String,
    val area: Area,
    val description: String,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
)

enum class Area {
    KISTA,
    SOLNA,
}

fun toArea(str: String): Area =
    Area.valueOf(str)

fun ParkingSpot.toIntent(action: String): Intent {
    return Intent(action).apply {
        data = "geo:$latitude,$longitude".toUri()
    }
}
