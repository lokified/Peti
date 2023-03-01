package com.loki.peti.presentation.register

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.presentation.PetiAppViewModel
import com.loki.peti.presentation.navigation.Screens

class RegisterViewModel: PetiAppViewModel() {


    val registerState = FormState(
        fields = listOf(
            TextFieldState(
                name = "firstName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "lastName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "email",
                validators = listOf(
                    Validators.Required(),
                    Validators.Email()
                )
            ),
            TextFieldState(
                name = "password",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "confirmPassword",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        openScreen: (String) -> Unit
    ) {

        launchCatching {
            openScreen(Screens.LoginScreen.route)
        }
    }
}