package se.daresay.domain.usecase

import kotlinx.coroutines.flow.Flow
import se.daresay.domain.base.BaseUseCase
import se.daresay.domain.base.Response
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository

class LogIn constructor(
    val repository: LoginRepository
): BaseUseCase<User, Boolean>() {
    override fun invoke(input: User): Flow<Response<Boolean>> =
        repository.logIn(input)
}