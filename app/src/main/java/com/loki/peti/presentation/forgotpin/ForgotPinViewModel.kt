package com.loki.peti.presentation.forgotpin

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.presentation.PetiAppViewModel
import com.loki.peti.presentation.navigation.Screens

class ForgotPinViewModel: PetiAppViewModel() {


    val forgotPinState = FormState(
        fields = listOf(
            TextFieldState(
                name = "email",
                validators = listOf(
                    Validators.Required(),
                    Validators.Email()
                )
            ),
            TextFieldState(
                name = "new_password",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "con_password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    fun changePassword(
        email: String,
        newPassword: String,
        popUp: () -> Unit
    ) {

        launchCatching {

            popUp()
        }
    }
}