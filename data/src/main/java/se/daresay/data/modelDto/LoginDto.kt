package se.daresay.data.modelDto

import se.daresay.data.base.ToDomain
import se.daresay.domain.model.Login
import se.daresay.domain.model.User

data class LoginDto(
    val token: String?,
    val message: String
): ToDomain<Login>{
    override fun toDomain(): Login =
        Login(
            token, message
        )

}

