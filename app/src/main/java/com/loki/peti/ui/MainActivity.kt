package com.loki.peti.ui

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.peti.ui.common.BottomNav
import com.loki.peti.ui.navigation.Navigation
import com.loki.peti.ui.theme.PetiTheme
import com.loki.peti.util.SnackbarManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            PetiTheme {

                val appState = rememberAppState()
                
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = it,
                            snackbar = { snackbarData ->

                                Snackbar(
                                    snackbarData = snackbarData,
                                    contentColor = Color.Black
                                )
                            }
                        )
                    },
                    scaffoldState = appState.scaffoldState,
                    bottomBar = {
                        BottomNav(
                            navController = appState.navController,
                            onItemClick = { appState.clearAndNavigate(it.route) }
                        )
                    }
                ) { padding ->

                    Navigation(appState = appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState,navController, snackbarManager, resources, coroutineScope) {
    PetiAppState(
        scaffoldState = scaffoldState,
        navController = navController,
        snackbarManager = snackbarManager,
        resources = resources,
        coroutineScope = coroutineScope
    )
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}