package com.nexters.ziine.android.presentation.magazine

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavGraphBuilder.magazineScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Route.Magazine> {
        MagazineRoute(
            padding = padding,
            modifier = modifier,
        )
    }
}
