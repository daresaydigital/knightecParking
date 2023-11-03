package se.daresay.domain.usecase

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.base.BaseUseCase
import se.daresay.domain.base.Response
import se.daresay.domain.model.Login
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository

class LogIn(val repository: LoginRepository){
    operator fun invoke(input: User): Flow<Response<Login>> =
        repository.logIn(input)
}