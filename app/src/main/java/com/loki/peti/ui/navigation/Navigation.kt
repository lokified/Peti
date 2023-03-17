package com.loki.peti.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.loki.peti.ui.PetiAppState
import com.loki.peti.ui.add.AddScreen
import com.loki.peti.ui.createProfile.ProfileSetup1Screen
import com.loki.peti.ui.createProfile.ProfileSetup2Screen
import com.loki.peti.ui.createProfile.ProfileSetup3Screen
import com.loki.peti.ui.createProfile.ProfileSetup4Screen
import com.loki.peti.ui.forgotpin.ForgotPinScreen
import com.loki.peti.ui.home.HomeScreen
import com.loki.peti.ui.login.LoginScreen
import com.loki.peti.ui.profile.ProfileScreen
import com.loki.peti.ui.register.RegisterScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    appState: PetiAppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = OnBoardingScreens.LoginScreen.route
    ) {

        composable(route = OnBoardingScreens.LoginScreen.route) {
            LoginScreen(
                openScreen = { route ->
                    appState.navigate(route)
                },
                openAndPopUp = { route, popUp ->
                    appState.navigateAndPopUp(route, popUp)
                }
            )
        }

        composable(route = OnBoardingScreens.ForgotPasswordScreen.route) {
            ForgotPinScreen(
                popUp = {
                    appState.popUp()
                }
            )
        }

        composable(route = OnBoardingScreens.RegisterScreen.route) {

            RegisterScreen(
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }

        composable(route = OnBoardingScreens.ProfileSetup1Screen.route) {

            ProfileSetup1Screen(
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }
        
        composable(route = OnBoardingScreens.ProfileSetup2Screen.route) {

            ProfileSetup2Screen(
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }

        composable(route = OnBoardingScreens.ProfileSetup3Screen.route) {

            ProfileSetup3Screen { route ->
                appState.navigate(route)
            }
        }

        composable(route = OnBoardingScreens.ProfileSetup4Screen.route) {

            ProfileSetup4Screen(
                clearAndOpen = { route ->
                    appState.clearAndNavigate(route)
                }
            )
        }

        homeScreenNavGraph(appState)
    }
}

sealed class OnBoardingScreens(val route: String) {

    object LoginScreen: OnBoardingScreens("Login_screen")
    object RegisterScreen: OnBoardingScreens("Register_screen")
    object ForgotPasswordScreen: OnBoardingScreens("ForgotPassword_screen")
    object ProfileSetup1Screen: OnBoardingScreens("profile_setup_1_screen")
    object ProfileSetup2Screen: OnBoardingScreens("profile_setup_2_screen")
    object ProfileSetup3Screen: OnBoardingScreens("profile_setup_3_screen")
    object ProfileSetup4Screen: OnBoardingScreens("profile_setup_4_screen")
}

fun NavGraphBuilder.homeScreenNavGraph(
    appState: PetiAppState
) {

    navigation(
        route = "Home_NavGraph",
        startDestination = HomeScreens.HomeScreen.route
    ) {

        composable(route = HomeScreens.HomeScreen.route) {

            HomeScreen { route ->
                    appState.navigate(route)
            }
        }

        composable(route = HomeScreens.AddScreen.route) {

            AddScreen( popUp = { appState.popUp() } )
        }

        composable(route = HomeScreens.ProfileScreen.route) {

            ProfileScreen()
        }

        composable(route = HomeScreens.ExpenseScreen.route) {

        }

        composable(route = HomeScreens.FoodScreen.route) {

        }

        composable(route = HomeScreens.HygieneScreen.route) {

        }

        composable(route = HomeScreens.ActivityScreen.route) {

        }

        composable(route = HomeScreens.VaccineScreen.route) {

        }
    }
}

sealed class HomeScreens(val route: String) {

    object HomeScreen: HomeScreens("home_screen")
    object AddScreen: HomeScreens("add_screen")
    object ProfileScreen: HomeScreens("profile_screen")
    object FoodScreen: HomeScreens("Food_screen")
    object ExpenseScreen: HomeScreens("Food_screen")
    object VaccineScreen: HomeScreens("Food_screen")
    object HygieneScreen: HomeScreens("Food_screen")
    object ActivityScreen: HomeScreens("Food_screen")
}