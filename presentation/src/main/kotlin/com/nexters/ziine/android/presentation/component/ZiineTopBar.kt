package com.nexters.ziine.android.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.navigation.MainTab
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Subtitle2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ZiineTopBar(
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(66.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEach { tab ->
                    ZiineTopBarItem(
                        tab = tab,
                        selected = currentTab == tab,
                        onClick = {
                            if (tab != currentTab) {
                                onTabSelected(tab)
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun ZiineTopBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(100.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
    ) {
        Text(
            text = tab.label,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            style = Subtitle2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@ComponentPreview
@Composable
private fun MainBottomBarPreview() {
    ZiineTheme {
        ZiineTopBar(
            visible = true,
            tabs = MainTab.entries.toImmutableList(),
            currentTab = MainTab.ARTWORKS,
            onTabSelected = {},
        )
    }
}
