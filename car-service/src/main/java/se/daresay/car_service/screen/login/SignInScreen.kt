package se.daresay.car_service.screen.login

import androidx.activity.OnBackPressedCallback
import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.InputCallback
import androidx.car.app.model.ParkedOnlyOnClickListener
import androidx.car.app.model.Template
import androidx.car.app.model.signin.InputSignInMethod
import androidx.car.app.model.signin.SignInTemplate
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.db.TOKEN
import se.daresay.car_service.db.save
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.parkings.ParkingOfficeListScreen
import se.daresay.domain.base.Response

class SignInScreen(carContext: CarContext): BaseScreen(carContext) {
    private var viewModel : SignInViewModel = get(SignInViewModel::class.java)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        configViewModel()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewModel.signInState.value is SignInState.Password)
                    viewModel.backToUsername()
            }
        }
        carContext.onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onGetTemplate(): Template {
        with(viewModel.signInState.value){
            when (
                this
            ) {
                is SignInState.Username -> {
                    return username(this)
                }
                is SignInState.Password -> {
                    return password(this)
                }
            }
        }

    }

    private fun username(state: SignInState.Username): SignInTemplate {
        val callback = object : InputCallback {
            override fun onInputSubmitted(text: String) {
                viewModel.goToPassword()
            }

            override fun onInputTextChanged(text: String) {
                viewModel.updateUsername(text)
            }
        }

        val actionButton = Action.Builder()
            .setTitle("Next")
            .setOnClickListener(ParkedOnlyOnClickListener.create {
                viewModel.goToPassword()
            })
            .build()

        val userNameInput = InputSignInMethod.Builder(callback)
            .setHint("Username")
            .setInputType(InputSignInMethod.INPUT_TYPE_DEFAULT)
            .build()

        val signIn = SignInTemplate.Builder(userNameInput)
            .setTitle("Sign In")
            .setInstructions("Enter your username")
            .addAction(actionButton)
            .setHeaderAction(Action.APP_ICON)

        state.error?.let {
            signIn.setAdditionalText(it)
        }

        return signIn.build()
    }

    private fun password(state: SignInState.Password): SignInTemplate {
        val callback = object : InputCallback {
            override fun onInputSubmitted(text: String) {
                viewModel.login()
            }

            override fun onInputTextChanged(text: String) {
                viewModel.updatePassword(text)
            }
        }

        val actionButton = Action.Builder()
            .setTitle("Sign In")
            .setOnClickListener(ParkedOnlyOnClickListener.create {
                viewModel.login()
            })
            .build()

        val userNameInput = InputSignInMethod.Builder(callback)
            .setHint("Password")
            .setInputType(InputSignInMethod.INPUT_TYPE_PASSWORD)
            .build()

        val signIn = SignInTemplate.Builder(userNameInput)
            .setTitle("Sign In")
            .setInstructions("Enter your password")
            .addAction(actionButton)
            .setHeaderAction(Action.BACK)

        state.error?.let {
            signIn.setAdditionalText(it)
        }

        return signIn.build()
    }

    private fun configViewModel(){
        lifecycleScope.launch {
            launch {
                viewModel.signInState.collect {
                    invalidate()
                }
            }
            launch {
                viewModel.loginState.collect {
                    when (it){
                        is Response.Idle -> {}
                        is Response.Error -> {
                            viewModel.errorSignIn(it.exception.message?:"Error")
                        }
                        is Response.Loading -> {
                            // TODO should we add loading here ?
                        }
                        is Response.Data -> {
                            if (it.data.token == null)
                                viewModel.errorSignIn(it.data.message)
                            else {
                                carContext.save(TOKEN, it.data.token!!)
                                screenManager.push(ParkingOfficeListScreen(carContext))
                            }
                        }
                    }
                }
            }
        }
    }
}