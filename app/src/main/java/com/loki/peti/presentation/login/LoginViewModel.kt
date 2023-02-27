package com.loki.peti.presentation.login

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.presentation.PetiAppViewModel
import com.loki.peti.presentation.navigation.Screens

class LoginViewModel: PetiAppViewModel() {


    val loginState = FormState(
        fields = listOf(
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
            )
        )
    )


    fun login(
        email: String,
        password: String,
        openAndPopUp: (String, String) -> Unit
    ) {

        launchCatching {

            openAndPopUp(
                Screens.HomeScreen.route,
                Screens.LoginScreen.route
            )
        }
    }
}