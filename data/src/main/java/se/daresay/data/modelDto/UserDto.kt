package se.daresay.data.modelDto

import se.daresay.data.base.ToDomain
import se.daresay.domain.model.User

data class UserDto(
    val username: String,
    val passwordHash: Int,
)