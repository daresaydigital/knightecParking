package se.daresay.car_service.screen.login

import se.daresay.domain.model.User

sealed class SignInState(
    val error: String?
) {
    class Username(error: String?): SignInState(error)
    class Password(error: String?): SignInState(error)
}