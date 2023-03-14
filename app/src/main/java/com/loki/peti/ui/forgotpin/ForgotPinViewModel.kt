package com.loki.peti.ui.forgotpin

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

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