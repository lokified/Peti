package com.loki.peti.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.peti.presentation.PetiAppState
import com.loki.peti.presentation.forgotpin.ForgotPinScreen
import com.loki.peti.presentation.login.LoginScreen
import com.loki.peti.presentation.register.RegisterScreen

@Composable
fun Navigation(appState: PetiAppState) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                openScreen = { route ->
                    appState.navigate(route)
                },
                openAndPopUp = { route, popUp ->
                    appState.navigateAndPopUp(route, popUp)
                }
            )
        }

        composable(route = Screens.ForgotPasswordScreen.route) {
            ForgotPinScreen(
                popUp = {
                    appState.popUp()
                }
            )
        }

        composable(route = Screens.RegisterScreen.route) {

            RegisterScreen(
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }
    }

}

sealed class Screens(val route: String) {

    object LoginScreen: Screens("Login_screen")
    object RegisterScreen: Screens("Register_screen")
    object HomeScreen: Screens("Home_screen")
    object AccountScreen: Screens("Account_screen")
    object ForgotPasswordScreen: Screens("ForgotPassword_screen")
}