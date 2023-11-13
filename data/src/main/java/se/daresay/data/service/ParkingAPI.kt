package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.OfficeDtoResponse
import se.daresay.data.modelDto.ParkingSpotDtoResponse

interface ParkingAPI {
    @GET("master/config/parking_spots.json")
    suspend fun getAllParking(): ParkingSpotDtoResponse

    @GET("master/config/parking_areas.json")
    suspend fun getAllAreas(): OfficeDtoResponse
}
