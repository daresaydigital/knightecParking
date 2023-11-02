package se.daresay.data.modelDto

import se.daresay.domain.model.User

data class UserDto(
    val username: String, val password: String
)

internal fun User.toDto() =
    UserDto(
        username, password
    )
