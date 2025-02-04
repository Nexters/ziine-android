package com.nexters.ziine.android.presentation.registerArtwork.register

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.registerArtwork.navigation.RegisterArtWorkRoute

fun NavController.navigateToRegister(navOptions: NavOptions) {
    navigate(RegisterArtWorkRoute.Register, navOptions)
}

fun NavGraphBuilder.registerScreen(modifier: Modifier = Modifier) {
    composable<RegisterArtWorkRoute.Register> {
        RegisterRoute(
            modifier = modifier,
        )
    }
}
