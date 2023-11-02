package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.ParkingSpotDto
import se.daresay.data.modelDto.ParkingSpotDtoResponse

interface ParkingAPI {
    @GET("23ad99c29840239107aa42846df6c3ea/raw/27461b8f39c328ce8c97731312aec0890461211d/response.json")
    suspend fun getAllParking(): ParkingSpotDtoResponse
}