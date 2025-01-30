package com.nexters.ziine.android.presentation.magazine

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.MainTabRoute

fun NavController.navigateToMagazine(navOptions: NavOptions) {
    navigate(MainTabRoute.Magazine, navOptions)
}

fun NavGraphBuilder.magazineScreen(modifier: Modifier = Modifier) {
    composable<MainTabRoute.Magazine> {
        MagazineRoute(
            modifier = modifier,
        )
    }
}
