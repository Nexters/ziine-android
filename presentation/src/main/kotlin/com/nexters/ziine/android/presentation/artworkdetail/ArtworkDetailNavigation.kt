package com.nexters.ziine.android.presentation.artworkdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavController.navigateToArtworkDetail(
    id: Int,
    imageUrl: String,
    title: String
) {
    navigate(Route.ArtworkDetail(id, imageUrl, title))
}

fun NavGraphBuilder.artworkDetailScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable<Route.ArtworkDetail> {
        ArtworkDetailRoute(
            padding = padding,
            popBackStack = popBackStack,
            animatedVisibilityScope = this@composable,
        )
    }
}
