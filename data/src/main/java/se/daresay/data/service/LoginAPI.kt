package se.daresay.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import se.daresay.data.modelDto.LoginDto
import se.daresay.data.modelDto.UserDto

interface LoginAPI {
    @POST("/idkURL")
    suspend fun logIn(@Body user: UserDto): LoginDto
}