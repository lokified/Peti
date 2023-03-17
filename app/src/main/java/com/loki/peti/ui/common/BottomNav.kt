package com.loki.peti.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.loki.peti.ui.navigation.HomeScreens

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val bottomBarDestination = navItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.background
        ) {

            navItems.forEach { bottomNavItem ->

                val selected = bottomNavItem.route == backStackEntry?.destination?.route

                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(bottomNavItem) },
                    icon = {

                        Icon(
                            imageVector = bottomNavItem.icon,
                            contentDescription = null,
                            modifier = Modifier.size(
                                if (bottomNavItem.icon == Icons.Rounded.AddCircle) 35.dp else 25.dp
                            ).alpha(1f)
                        )
                    },
                    label = {
                        Text(text = bottomNavItem.navTitle)
                    }
                )
            }
        }
    }
}


data class BottomNavItem(
    val icon: ImageVector,
    val navTitle: String,
    val route: String
)


val navItems = listOf(

    BottomNavItem(
        icon = Icons.Rounded.Home,
        navTitle = "Home",
        route = HomeScreens.HomeScreen.route
    ),
    BottomNavItem(
        icon = Icons.Rounded.AddCircle,
        navTitle = "",
        route = HomeScreens.AddScreen.route
    ),
    BottomNavItem(
        icon = Icons.Rounded.AccountCircle,
        navTitle = "Profile",
        route = HomeScreens.ProfileScreen.route
    )
)