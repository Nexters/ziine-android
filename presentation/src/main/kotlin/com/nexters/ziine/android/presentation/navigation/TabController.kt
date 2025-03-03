package com.nexters.ziine.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

class TabController(
    private val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    private var lastSelectedTab = mutableStateOf(MainTab.ARTWORKS)

    val currentTab: MainTab
        @Composable get() {
            val tabFromDestination = MainTab.find { tab ->
                currentDestination?.hasRoute(tab::class) == true
            }

            if (currentDestination?.hasRoute<Route.MagazineDetail>() == true) {
                if (lastSelectedTab.value != MainTab.MAGAZINE) {
                    lastSelectedTab.value = MainTab.MAGAZINE
                }
                return MainTab.MAGAZINE
            }

            // 메인 탭 화면에 있을 때 마지막 선택된 탭 업데이트
            if (tabFromDestination != null) {
                lastSelectedTab.value = tabFromDestination
            }

            // 현재 탭 또는 마지막 선택된 탭 반환
            return tabFromDestination ?: lastSelectedTab.value
        }

    @Composable
    fun shouldShowTopBar() =
        MainTab.contains {
            currentDestination?.hasRoute(it::class) == true
        }

    @Composable
    fun shouldShowFloatingActionButton() = currentDestination?.hasRoute(MainTab.ARTWORKS.route::class) == true
}
