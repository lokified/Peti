package com.loki.peti.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.R
import com.loki.peti.presentation.common.ButtonSection
import com.loki.peti.presentation.common.Input
import com.loki.peti.presentation.login.LoginViewModel
import com.loki.peti.presentation.navigation.Screens

@Composable
fun RegisterScreen(
    openScreen: (String) -> Unit,
) {


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        TopSection(
            modifier = Modifier.padding(top = 24.dp)
        )

        FormSection(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
            openScreen = openScreen
        )
    }

}


@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
        ) {

            Image(
                painter = painterResource(id = R.drawable.cat_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Create an Account",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormSection(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    openScreen: (String) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.registerState }

    val firstName = formState.getState<TextFieldState>("firstName")
    val lastName = formState.getState<TextFieldState>("lastName")
    val email = formState.getState<TextFieldState>("email")
    val password = formState.getState<TextFieldState>("password")
    val conPassword = formState.getState<TextFieldState>("confirmPassword")

    val isPasswordMatch = password.value === conPassword.value


    Box(modifier = modifier.fillMaxWidth()) {

        Column {
            Input(
                label="First Name",
                placeholder = "First Name",
                value = firstName.value,
                onValueChange = { firstName.change(it) },
                errorMessage = firstName.errorMessage,
                isError = firstName.hasError,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Input(
                label="Last Name",
                placeholder = "Last Name",
                value = lastName.value,
                onValueChange = { lastName.change(it) },
                errorMessage = lastName.errorMessage,
                isError = lastName.hasError,
                modifier = Modifier.padding(vertical = 4.dp)
            )

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
                label = "Password",
                placeholder = "Password",
                value = password.value,
                onValueChange = { password.change(it) },
                errorMessage = password.errorMessage,
                isError = password.hasError,
                modifier = Modifier.padding(vertical = 4.dp),
                keyboardType = KeyboardType.Password
            )

            Input(
                label = "Confirm Password",
                placeholder = "Confirm Password",
                value = conPassword.value,
                onValueChange = { conPassword.change(it) },
                errorMessage = if (!isPasswordMatch) "Password does not match" else conPassword.errorMessage,
                isError = conPassword.hasError && !isPasswordMatch,
                modifier = Modifier.padding(vertical = 4.dp),
                keyboardType = KeyboardType.Password
            )

            ButtonSection(text = "Register", modifier = Modifier.padding(top = 24.dp)) {

                keyboardController?.hide()

                if (formState.validate()) {
                    viewModel.register(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        email = email.value,
                        password = password.value,
                        openScreen = { route ->
                            openScreen(route)
                        }
                    )
                }
            }
        }
    }
}