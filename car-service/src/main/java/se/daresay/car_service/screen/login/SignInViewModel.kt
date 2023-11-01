package se.daresay.car_service.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import se.daresay.domain.base.Response
import se.daresay.domain.model.User
import se.daresay.domain.usecase.LogIn
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class SignInViewModel @Inject constructor(
    val logIn: LogIn
): ViewModel() {

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