package se.daresay.domain.repo

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.base.Response
import se.daresay.domain.model.User

interface LoginRepository {
    fun logIn(user: User): Flow<Response<Boolean>>
}