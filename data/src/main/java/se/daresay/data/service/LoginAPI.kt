package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.UserDto

interface LoginAPI {
    @GET("/75ce84e522ba6cf21abe3cfb3e15cf7d/raw/fa023ae7292f712ed236d4d77b49b0a54fe702ce/response.json")
    suspend fun logIn(): List<UserDto>
}