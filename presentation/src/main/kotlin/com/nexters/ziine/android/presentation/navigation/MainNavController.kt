package com.nexters.ziine.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.nexters.ziine.android.presentation.artworks.navigateToArtworks
import com.nexters.ziine.android.presentation.magazine.navigateToMagazine

class MainNavController(
    val navController: NavHostController,
) {
    val startDestination: MainTabRoute = MainTab.ARTWORKS.route
    val tabController = TabController(navController)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.ARTWORKS -> navController.navigateToArtworks(navOptions)
            MainTab.MAGAZINE -> navController.navigateToMagazine(navOptions)
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    // https://github.com/droidknights/DroidKnights2023_App/pull/243/commits/4bfb6d13908eaaab38ab3a59747d628efa3893cb
    fun popBackStackIfNotArtworks() {
        if (!isSameCurrentDestination<MainTabRoute.Artworks>()) {
            popBackStack()
        }
    }

    fun clearBackStack() {
        val options = NavOptions.Builder()
            .setPopUpTo(navController.graph.findStartDestination().id, inclusive = false)
            .build()
        navController.navigate(startDestination, options)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }
}

@Composable
fun rememberMainNavController(navController: NavHostController = rememberNavController()): MainNavController =
    remember(navController) {
        MainNavController(navController)
    }
