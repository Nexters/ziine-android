package com.nexters.ziine.android.presentation.registerArtwork.register

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.registerArtwork.navigation.RegisterArtWorkRoute

fun NavController.navigateToRegister() {
    navigate(RegisterArtWorkRoute.Register)
}

fun NavGraphBuilder.registerScreen(
    modifier: Modifier = Modifier,
    backToPrevious: () -> Unit,
    navigateToComplete: () -> Unit
) {
    composable<RegisterArtWorkRoute.Register> {
        RegisterRoute(
            modifier = modifier,
            backToPrevious = backToPrevious,
            navigateToComplete = navigateToComplete,
        )
    }
}
