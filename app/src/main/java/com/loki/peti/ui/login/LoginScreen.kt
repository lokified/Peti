package com.loki.peti.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.Input
import com.loki.peti.ui.navigation.OnBoardingScreens

@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {

    Column {
        TopSection(
            modifier = Modifier.padding(top = 24.dp)
        )

        FormSection(
            modifier = Modifier.padding(16.dp),
            openAndPopUp = openAndPopUp,
            openScreen = openScreen
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Center
        ) {
            
            Text(
                text = "Don't have an account? Register",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp).clickable {
                    openScreen(OnBoardingScreens.RegisterScreen.route)
                }
            )
        }
    }

}


@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
        ) {

            Image(
                painter = painterResource(id = R.drawable.cat_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(CenterHorizontally)
            )

            Text(
                text = "Sign In",
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
    viewModel: LoginViewModel = hiltViewModel(),
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.loginState }

    val email = formState.getState<TextFieldState>("email")
    val password = formState.getState<TextFieldState>("password")

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
                label = "Password",
                placeholder = "Password",
                value = password.value,
                onValueChange = { password.change(it) },
                errorMessage = password.errorMessage,
                isError = password.hasError,
                modifier = Modifier.padding(vertical = 4.dp),
                keyboardType = KeyboardType.Password
            )

            Text(
                text = "Forgot Password ?",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    vertical = 8.dp
                ).clickable {
                    openScreen(OnBoardingScreens.ForgotPasswordScreen.route)
                }
            )

            ButtonSection(text = "Sign In", modifier = Modifier.padding(top = 24.dp)) {

                keyboardController?.hide()

                if (formState.validate()) {
                    viewModel.login(
                        email = email.value,
                        password = password.value,
                        openAndPopUp = { route, popup ->
                            openAndPopUp(route, popup)
                        }
                    )
                }
            }
        }
    }
}