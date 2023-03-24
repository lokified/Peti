package com.loki.peti.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.loki.peti.ui.PetiAppState
import com.loki.peti.ui.add.AddScreen
import com.loki.peti.ui.addActivities.AddActivitiesScreen
import com.loki.peti.ui.addExpense.AddExpenseScreen
import com.loki.peti.ui.addFood.AddFoodScreen
import com.loki.peti.ui.addHygiene.AddHygieneScreen
import com.loki.peti.ui.addNewSubCategory.AddNewSubCategoryScreen
import com.loki.peti.ui.addVaccine.AddVaccineScreen
import com.loki.peti.ui.createProfile.ProfileSetup1Screen
import com.loki.peti.ui.createProfile.ProfileSetup2Screen
import com.loki.peti.ui.createProfile.ProfileSetup3Screen
import com.loki.peti.ui.createProfile.ProfileSetup4Screen
import com.loki.peti.ui.forgotpin.ForgotPinScreen
import com.loki.peti.ui.home.HomeScreen
import com.loki.peti.ui.homeDetail.HomeDetailScreen
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

        val title = mutableStateOf("")

        composable(route = HomeScreens.HomeScreen.route) {

            HomeScreen (
                openScreen = { route ->
                    appState.navigate(route)
                },
                titleClicked = { title.value = it }
            )
        }

        composable(route = HomeScreens.AddScreen.route) {

            AddScreen(
                openAndPopUp = { route, popup ->
                    appState.navigateAndPopUp(route, popup)
                }
            )
        }

        composable(route = HomeScreens.ProfileScreen.route) {

            ProfileScreen(clearAndOpen = { appState.clearAndNavigate(it) })
        }

        composable(route = HomeScreens.HomeDetailScreen.route) {

            HomeDetailScreen(
                topBarTitle = title.value,
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }

        composable(route = HomeScreens.AddVaccineScreen.route) {

            AddVaccineScreen(
                topBarTitle = title.value,
                popUp =  { appState.popUp() }
            )
        }

        composable(route = HomeScreens.AddFoodScreen.route) {

            AddFoodScreen(
                topBarTitle = title.value,
                popUp = { appState.popUp() }
            )
        }

        composable(route = HomeScreens.AddHygieneScreen.route) {

            AddHygieneScreen(
                topBarTitle = title.value,
                popUp = { appState.popUp() }
            )
        }

        composable(route = HomeScreens.AddActivityScreen.route) {

            AddActivitiesScreen(
                topBarTitle = title.value,
                popUp = { appState.popUp() }
            )
        }

        composable(route = HomeScreens.AddExpenseScreen.route) {

            AddExpenseScreen(
                topBarTitle = title.value,
                popUp = { appState.popUp() }
            )
        }

        composable(route = HomeScreens.AddNewCategoryScreen.route) {

            AddNewSubCategoryScreen(topBarTitle = title.value) {
                appState.popUp()
            }
        }
    }
}

sealed class HomeScreens(val route: String) {

    object HomeScreen: HomeScreens("home_screen")
    object AddScreen: HomeScreens("add_screen")
    object ProfileScreen: HomeScreens("profile_screen")
    object HomeDetailScreen: HomeScreens("Home_Detail_screen")
    object AddVaccineScreen: HomeScreens("Add_Vaccine_screen")
    object AddExpenseScreen: HomeScreens("Add_Expense_screen")
    object AddHygieneScreen: HomeScreens("Add_Hygiene_screen")
    object AddFoodScreen: HomeScreens("Add_Food_screen")
    object AddActivityScreen: HomeScreens("Add_Activity_screen")
    object AddNewCategoryScreen: HomeScreens("Add_new_category_screen")
}