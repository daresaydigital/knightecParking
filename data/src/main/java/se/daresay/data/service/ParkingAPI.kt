package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.OfficeDtoResponse
import se.daresay.data.modelDto.ParkingSpotDtoResponse

interface ParkingAPI {
    @GET("23ad99c29840239107aa42846df6c3ea/raw/2ad111a69b318e632b174cee48761bbd016bc295/response.json")
    suspend fun getAllParking(): ParkingSpotDtoResponse

    @GET("3963ddded054fa1d9ef537ab5ca5b079/raw/39acad26ebd5d9e07aaaba2eb5513e62e453eb23/response.json")
    suspend fun getAllAreas(): OfficeDtoResponse
}