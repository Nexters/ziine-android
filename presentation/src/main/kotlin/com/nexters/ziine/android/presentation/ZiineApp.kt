package com.nexters.ziine.android.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.nexters.ziine.android.presentation.artworkdetail.artworkDetailScreen
import com.nexters.ziine.android.presentation.artworks.artworksScreen
import com.nexters.ziine.android.presentation.component.ZiineTopBar
import com.nexters.ziine.android.presentation.magazine.magazineScreen
import com.nexters.ziine.android.presentation.navigation.MainTab
import com.nexters.ziine.android.presentation.navigation.rememberMainNavController
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ZiineApp(modifier: Modifier = Modifier) {
    val mainNavController = rememberMainNavController()
    val tabController = mainNavController.tabController

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            ZiineTopBar(
                visible = tabController.shouldShowTopBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = tabController.currentTab,
                onTabSelected = {
                    mainNavController.navigate(it)
                },
                modifier = Modifier
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    )
                    .padding(top = 20.dp, bottom = 8.dp),
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = tabController.shouldShowTopBar(),
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                    )
                }
            }
        },
    ) { padding ->
        SharedTransitionLayout {
            CompositionLocalProvider(
                LocalSharedTransitionScope provides this@SharedTransitionLayout,
            ) {
                NavHost(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    navController = mainNavController.navController,
                    startDestination = mainNavController.startDestination,
                ) {
                    artworksScreen(navigateToArtworkDetail = mainNavController::navigateToArtworkDetail,)
                    magazineScreen()
                    artworkDetailScreen(popBackStack = mainNavController::popBackStackIfNotArtworks)
                }
            }
        }
    }
}
