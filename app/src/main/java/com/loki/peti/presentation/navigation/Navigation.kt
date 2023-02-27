package com.loki.peti.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.peti.presentation.PetiAppState
import com.loki.peti.presentation.login.LoginScreen

@Composable
fun Navigation(appState: PetiAppState) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                openScreen = { route -> },
                openAndPopUp = { route, popUp ->
                    appState.navigateAndPopUp(route, popUp)
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