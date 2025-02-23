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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.nexters.ziine.android.presentation.artworkdetail.artworkDetailScreen
import com.nexters.ziine.android.presentation.artworks.artworksScreen
import com.nexters.ziine.android.presentation.component.ZiineTopBar
import com.nexters.ziine.android.presentation.magazine.magazineScreen
import com.nexters.ziine.android.presentation.magazinedetail.magazineDetailScreen
import com.nexters.ziine.android.presentation.navigation.MainTab
import com.nexters.ziine.android.presentation.navigation.rememberMainNavController
import com.nexters.ziine.android.presentation.registerArtwork.RegisterArtworkActivity
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ZiineApp(modifier: Modifier = Modifier) {
    val mainNavController = rememberMainNavController()
    val tabController = mainNavController.tabController
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                isVisible = available.y >= 0
                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(nestedScrollConnection),
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
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
                    )
                    .padding(top = 20.dp, bottom = 8.dp),
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = tabController.shouldShowFloatingActionButton() && isVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                FloatingActionButton(
                    onClick = { with(context) { startActivity(RegisterArtworkActivity.getIntent(this)) } },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_add),
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
                    modifier = modifier.fillMaxSize(),
                    navController = mainNavController.navController,
                    startDestination = mainNavController.startDestination,
                ) {
                    artworksScreen(
                        padding = padding,
                        navigateToArtworkDetail = mainNavController::navigateToArtworkDetail,
                    )
                    magazineScreen(
                        padding = padding,
                        navigateToMagazineDetail = mainNavController::navigateToMagazineDetail,
                    )
                    artworkDetailScreen(
                        padding = padding,
                        popBackStack = mainNavController::popBackStackIfNotArtworks,
                    )
                    magazineDetailScreen(
                        padding = padding,
                        backToPrevious = mainNavController::popBackStackIfNotArtworks,
                    )
                }
            }
        }
    }
}
