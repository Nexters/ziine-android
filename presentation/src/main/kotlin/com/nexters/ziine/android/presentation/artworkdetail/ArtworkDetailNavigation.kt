package com.nexters.ziine.android.presentation.artworkdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.LocalNavAnimatedVisibilityScope
import com.nexters.ziine.android.presentation.navigation.Route

fun NavController.navigateToArtworkDetail(
    id: Int,
    title: String,
    artworkImageUrl: String,
) {
    navigate(Route.ArtworkDetail(id = id, title = title, artworkImageUrl = artworkImageUrl))
}

fun NavGraphBuilder.artworkDetailScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable<Route.ArtworkDetail> {
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this@composable) {
            ArtworkDetailRoute(
                padding = padding,
                popBackStack = popBackStack,
            )
        }
    }
}
