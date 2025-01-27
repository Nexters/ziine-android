package com.nexters.ziine.android.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nexters.ziine.android.presentation.artworks.artworksScreen
import com.nexters.ziine.android.presentation.magazine.magazineScreen
import com.nexters.ziine.android.presentation.splash.splashScreen

@Composable
fun ZiineNavHost(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Magazine,
    ) {
        splashScreen(padding = padding)
        artworksScreen(padding = padding)
        magazineScreen(padding = padding)
    }
}