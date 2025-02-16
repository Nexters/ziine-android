package com.nexters.ziine.android.presentation.registerArtwork.pending

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.registerArtwork.navigation.RegisterArtWorkRoute

fun NavController.navigateToPending(navOptions: NavOptions) {
    navigate(RegisterArtWorkRoute.Pending, navOptions)
}

fun NavGraphBuilder.pendingScreen(
    modifier: Modifier = Modifier,
    activityFinishAction: () -> Unit,
    navigateToRegister: () -> Unit
) {
    composable<RegisterArtWorkRoute.Pending> {
        PendingRoute(
            modifier = modifier,
            activityFinishAction = activityFinishAction,
            navigateToRegister = navigateToRegister,
        )
    }
}
