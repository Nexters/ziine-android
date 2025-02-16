package com.nexters.ziine.android.presentation.magazinedetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavController.navigateToMagazineDetail(magazineId: Int) {
    navigate(Route.MagazineDetail(magazineId = magazineId))
}

fun NavGraphBuilder.magazineDetailScreen(
    padding: PaddingValues,
    backToPrevious: () -> Unit,
) {
    composable<Route.MagazineDetail> {
        MagazineDetailRoute(
            padding = padding,
            backToPrevious = backToPrevious,
        )
    }
}
