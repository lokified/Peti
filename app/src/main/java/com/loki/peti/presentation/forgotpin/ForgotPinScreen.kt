package com.loki.peti.presentation.forgotpin


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.presentation.common.ButtonSection
import com.loki.peti.presentation.common.Input
import com.loki.peti.presentation.common.TopBar

@Composable
fun ForgotPinScreen(
    popUp: () -> Unit
) {

    TopBar(title = "Change Password") {
        Column {

            FormSection(
                popUp = popUp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormSection(
    modifier: Modifier = Modifier,
    viewModel: ForgotPinViewModel = hiltViewModel(),
    popUp: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.forgotPinState }

    val email = formState.getState<TextFieldState>("email")
    val newPassword = formState.getState<TextFieldState>("new_password")
    val conPassword = formState.getState<TextFieldState>("con_password")

    val isPasswordMatch = newPassword === conPassword

    Box(modifier = modifier.fillMaxWidth()) {

        Column {
            Input(
                label="Email",
                placeholder = "Email",
                value = email.value,
                onValueChange = { email.change(it) },
                errorMessage = email.errorMessage,
                isError = email.hasError,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Input(
                label = "New Password",
                placeholder = "New Password",
                value = newPassword.value,
                onValueChange = { newPassword.change(it) },
                errorMessage = newPassword.errorMessage,
                isError = newPassword.hasError,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Input(
                label = "Confirm Password",
                placeholder = "Confirm Password",
                value = conPassword.value,
                onValueChange = { conPassword.change(it) },
                errorMessage = if (!isPasswordMatch) "Password does not match" else conPassword.errorMessage,
                isError = conPassword.hasError && !isPasswordMatch,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            ButtonSection(
                text = "Change Password",
                modifier = Modifier.padding(top = 24.dp)
            ) {

                keyboardController?.hide()

                if (formState.validate() && isPasswordMatch) {
                    viewModel.changePassword(
                        email = email.value,
                        newPassword = newPassword.value
                    ) {
                        popUp()
                    }
                }
            }
        }
    }
}
