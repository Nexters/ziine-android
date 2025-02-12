package com.nexters.ziine.android.presentation.registerArtwork.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nexters.ziine.android.presentation.registerArtwork.complete.completeScreen
import com.nexters.ziine.android.presentation.registerArtwork.complete.navigateToComplete
import com.nexters.ziine.android.presentation.registerArtwork.pending.pendingScreen
import com.nexters.ziine.android.presentation.registerArtwork.register.navigateToRegister
import com.nexters.ziine.android.presentation.registerArtwork.register.registerScreen

@Composable
fun RegisterArtWorkNavHost(
    modifier: Modifier = Modifier,
    activityFinishAction: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RegisterArtWorkRoute.Pending,
    ) {
        pendingScreen(activityFinishAction = activityFinishAction, navigateToRegister = navController::navigateToRegister)
        registerScreen(backToPrevious = { navController.popBackStack() }, navigateToComplete = navController::navigateToComplete)
        completeScreen(activityFinishAction = activityFinishAction)
    }
}
