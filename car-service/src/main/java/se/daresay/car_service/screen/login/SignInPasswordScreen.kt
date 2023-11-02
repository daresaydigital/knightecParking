package se.daresay.car_service.screen.login

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.InputCallback
import androidx.car.app.model.Template
import androidx.car.app.model.signin.InputSignInMethod
import androidx.car.app.model.signin.SignInTemplate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import se.daresay.car_service.screen.EmptyScreen
import se.daresay.domain.base.Response
import se.daresay.domain.model.User
import se.daresay.domain.repo.LoginRepository

class SignInPasswordScreen constructor(carContext: CarContext,private val userName: String) : Screen(carContext) {


    private lateinit var viewModel : SignInViewModel

    override fun onGetTemplate(): Template {
        viewModel = get(SignInViewModel::class.java)
        configViewModel()

        val callback = object : InputCallback {
            override fun onInputSubmitted(text: String) {
                validate(text)
            }

            override fun onInputTextChanged(text: String) {}
        }

        val passwordInput = InputSignInMethod.Builder(callback)
            .setHint("password")
            .setInputType(InputSignInMethod.INPUT_TYPE_PASSWORD)
            .build()

        val signIn = SignInTemplate.Builder(passwordInput)
            .setTitle("Sign In")
            .setInstructions("Enter your password")
            .build()

        return signIn
    }


    private fun configViewModel(){
        lifecycleScope.launch {
            viewModel._loginState.collect {
                when (it){
                    is Response.Idle -> {}
                    is Response.Error -> {
                        screenManager.push(SignInUserNameScreen(carContext,it.exception.message ?: "unknown error"))
                    }
                    is Response.Loading -> {
                        Log.d("HEREHERE","Loading")
                    }
                    is Response.Data -> {
                        if (it.data.token == null)
                            screenManager.push(SignInUserNameScreen(carContext,it.data.message))
                        else
                            screenManager.push(EmptyScreen(carContext))
                    }
                }
            }
        }
    }
    private fun validate(text: String){
        Log.d("HEREHERE","HERE")

        viewModel.login(User(userName, text))
    }
}