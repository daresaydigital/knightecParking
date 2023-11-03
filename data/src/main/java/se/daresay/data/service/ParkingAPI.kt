package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.AreaDto
import se.daresay.data.modelDto.ParkingSpotDto
import se.daresay.data.modelDto.ParkingSpotDtoResponse

interface ParkingAPI {
    @GET("23ad99c29840239107aa42846df6c3ea/raw/27461b8f39c328ce8c97731312aec0890461211d/response.json")
    suspend fun getAllParking(): ParkingSpotDtoResponse

    @GET("3963ddded054fa1d9ef537ab5ca5b079/raw/9dd6dfc7d153b052afc90791e61c385d26bbe1b1/response.json")
    suspend fun getAllAreas(): AreaDto
}