package se.daresay.data.repoImp

import kotlinx.coroutines.flow.Flow
import se.daresay.data.base.apiCall
import se.daresay.data.service.LoginAPI
import se.daresay.domain.base.Response
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository

class LoginRepositoryImp (private val api: LoginAPI): LoginRepository {
    override fun logIn(user: User): Flow<Response<Boolean>> =
        apiCall("Log In..."){
            var ret = false
            for(it in api.logIn() ){
                if (user.username == it.username && user.password.hashCode() == it.passwordHash){
                    ret = true
                }
                break
            }
            ret
        }
}