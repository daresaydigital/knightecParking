package se.daresay.car_service.screen.login

import android.R
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.model.InputCallback
import androidx.car.app.model.Template
import androidx.car.app.model.signin.InputSignInMethod
import androidx.car.app.model.signin.SignInTemplate
import dagger.hilt.android.scopes.ActivityRetainedScoped


@ActivityRetainedScoped
class SignInUserNameScreen(carContext: CarContext) : Screen(carContext) {

    val viewModel: SignInViewModel by viewModels()

    override fun onGetTemplate(): Template {

        val callback = object : InputCallback {
            override fun onInputSubmitted(text: String) {
                validate(text)
            }

            override fun onInputTextChanged(text: String) {}
        }

        val userNameInput = InputSignInMethod.Builder(callback)
            .setHint("username")
            .setKeyboardType(InputSignInMethod.KEYBOARD_EMAIL)
            .build()

        val signIn = SignInTemplate.Builder(userNameInput)
            .setTitle("Sign In")
            .setInstructions("Enter your username")
            .build()

        return signIn
    }

    private fun validate(userName: String){
        // TODO validate
    }
}