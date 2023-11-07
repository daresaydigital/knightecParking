package se.daresay.car_service.screen.login

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.db.Editor
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.domain.base.Response
import se.daresay.domain.model.Login
import se.daresay.domain.model.User
import se.daresay.domain.usecase.LogIn
import java.util.Date

class SignInViewModel(private val logIn: LogIn): ViewModel() {
    /**
     * this viewModel is used both for Compose layer and Car layer
     * [loginState] : both
     * [signInState] : car
     * [userInState] : both
     */

    private val editor: Editor = get(Editor::class.java)
    private val _loginState: MutableStateFlow<Login?> = MutableStateFlow(null)
    val loginState = _loginState.asStateFlow()

    private val _signInState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState.Username(null))
    val signInState = _signInState.asStateFlow()

    private val _userState: MutableStateFlow<User> = MutableStateFlow(User("", ""))
    val userInState = _userState.asStateFlow()
    init {
        viewModelScope.launch {
            editor.load(TOKEN).collect {
                it?.let { token ->
                    if (token != "null")
                        _loginState.update {
                            Login(
                                token = token,
                                message = ""
                            )
                        }
                }
            }
        }
    }



    fun login(){
        if (userInState.value.password.isNotEmpty() && userInState.value.username.isNotEmpty()) {
            viewModelScope.launch {
                logIn.invoke(userInState.value).collect{
                    when (it) {
                        is Response.Idle -> {}
                        is Response.Loading -> {}
                        is Response.Error -> {
                            errorSignIn(it.exception.message?:"Error")
                        }
                        is Response.Data -> {
                            if (it.data.token == null){
                                errorSignIn(it.data.message)
                            } else
                                editor.save(TOKEN, it.data.token!!)
                        }
                    }
                }
            }
        } else {
            _signInState.update {
                SignInState.Password(
                    "username or password can't be empty",
                )
            }

            // this line is for compose layer and not used in the other
            _loginState.update {
                Login(
                    null, "username or password can't be empty"
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
        _userState.update { it.copy(username = text) }
    }

    fun updatePassword(text: String) {
        _userState.update { it.copy(password = text) }
    }

    fun goToPassword() {
        if (userInState.value.username.isNotEmpty()) {
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

    private fun errorSignIn(error: String) {

        _loginState.update {
            Login(null, error)
        }
        _signInState.update {
            SignInState.Username(
                error,
            )
        }
        _loginState.update {
            Login(null, error)
        }
        _userState.update {
            User("", "")
        }
    }
}
