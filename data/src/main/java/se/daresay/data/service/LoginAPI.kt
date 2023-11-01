package se.daresay.data.service

import retrofit2.http.GET
import se.daresay.data.modelDto.UserDto

interface LoginAPI {
    @GET("23ad99c29840239107aa42846df6c3ea/raw/79212731aa1418254252f4dda376a086296fc82a/response.json")
    suspend fun logIn(): List<UserDto>
}