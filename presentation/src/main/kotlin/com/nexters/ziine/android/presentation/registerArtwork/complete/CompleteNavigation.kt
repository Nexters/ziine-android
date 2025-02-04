package com.nexters.ziine.android.presentation.registerArtwork.complete

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.registerArtwork.navigation.RegisterArtWorkRoute
import com.nexters.ziine.android.presentation.registerArtwork.pending.PendingRoute

fun NavController.navigateToComplete(navOptions: NavOptions) {
    navigate(RegisterArtWorkRoute.Complete, navOptions)
}

fun NavGraphBuilder.completeScreen(modifier: Modifier = Modifier) {
    composable<RegisterArtWorkRoute.Complete> {
        CompleteRoute(
            modifier = modifier,
        )
    }
}
