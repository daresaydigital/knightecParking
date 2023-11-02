package se.daresay.car_service.screen.login

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.InputCallback
import androidx.car.app.model.Template
import androidx.car.app.model.signin.InputSignInMethod
import androidx.car.app.model.signin.SignInTemplate

class SignInUserNameScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        val callback = object : InputCallback {
            override fun onInputSubmitted(text: String) {
                navigate(text)
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

    private fun navigate(userName: String){
        screenManager.push(SignInPasswordScreen(carContext, userName))
    }
}