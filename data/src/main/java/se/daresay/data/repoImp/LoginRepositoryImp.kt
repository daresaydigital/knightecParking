package se.daresay.data.repoImp

import kotlinx.coroutines.flow.Flow
import se.daresay.data.base.apiCall
import se.daresay.data.base.apiCallDomain
import se.daresay.data.modelDto.toDto
import se.daresay.data.service.LoginAPI
import se.daresay.domain.base.Response
import se.daresay.domain.model.Login
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository

class LoginRepositoryImp (private val api: LoginAPI): LoginRepository {
    override fun logIn(user: User): Flow<Response<Login>> =
        apiCallDomain("Log In..."){
            api.logIn(user.toDto())
        }
}