package se.daresay.car_service.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import se.daresay.domain.base.Response
import se.daresay.domain.model.Login
import se.daresay.domain.model.User
import se.daresay.domain.usecase.LogIn

class SignInViewModel(private val logIn: LogIn): ViewModel() {

    private val _loginState: MutableStateFlow<Response<Login>> = MutableStateFlow(Response.Idle())
    val loginState = _loginState.asStateFlow()

    private val _signInState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState.Username(
        null)
    )
    val signInState = _signInState.asStateFlow()

    private var user = User("", "")

    fun login(){
        if (user.password.isNotEmpty()) {
            viewModelScope.launch {
                logIn.invoke(user).collect{
                    _loginState.value = it
                }
            }
        } else {
            _signInState.update {
                SignInState.Password(
                    "password can't be empty",
                )
            }
        }
    }

    fun backToUsername() {
        _signInState.update {
            SignInState.Username(null)
        }
    }

    fun updateUsername(text: String) {
        user = user.copy(username = text)
    }

    fun updatePassword(text: String) {
        user = user.copy(password = text)
    }

    fun goToPassword() {
        if (user.username.isNotEmpty()) {
            _signInState.update {
                SignInState.Password(
                    null,
                )
            }
        } else {
            _signInState.update {
                SignInState.Username(
                    "username can't be empty",
                )
            }
        }
    }

    fun errorSignIn(error: String) {
        user = User("", "")
        _signInState.update {
            SignInState.Username(
                error,
            )
        }
        _loginState.update {
            Response.Idle()
        }
    }
}
