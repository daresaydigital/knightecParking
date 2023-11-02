package se.daresay.car_service.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import se.daresay.domain.base.Response
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository
import se.daresay.domain.usecase.LogIn

class SignInViewModel(private val logIn: LogIn): ViewModel() {

    private val loginState: MutableStateFlow<Response<Boolean>> = MutableStateFlow(Response.Idle())
    val _loginState = loginState.asStateFlow()

    fun login(user: User){
        viewModelScope.launch {
            logIn.invoke(user).collect{
                loginState.value = it
            }
        }
    }
}