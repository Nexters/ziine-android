package com.nexters.ziine.android.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nexters.ziine.android.presentation.navigation.MainTab
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Subtitle2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

private data class TabPosition(
    val offset: Int,
    val width: Int
)

@Composable
fun ZiineTopBar(
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(top = 20.dp, bottom = 8.dp),
        ) {
            var tabPositions by remember { mutableStateOf(List(tabs.size) { TabPosition(0, 0) }) }
            var selectedTabWidth by remember { mutableIntStateOf(0) }

            val targetOffset = if (currentTab != null) tabPositions[tabs.indexOf(currentTab)].offset else 0
            val animatedOffset by animateIntAsState(
                targetValue = targetOffset,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "tab offset animation"
            )

            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Surface(
                    modifier = Modifier
                        .offset { IntOffset(animatedOffset, 0) }
                        .width(with(density) { selectedTabWidth.toDp() })
                        .height(37.dp)
                        .zIndex(0f),
                    shape = RoundedCornerShape(100.dp),
                    color = MaterialTheme.colorScheme.primary
                ) {}

                Row(
                    modifier = Modifier.zIndex(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    tabs.forEachIndexed { index, tab ->
                        ZiineTopBarItem(
                            tab = tab,
                            selected = currentTab == tab,
                            onSelected = {
                                if (tab != currentTab) {
                                    onTabSelected(tab)
                                }
                            },
                            onPositioned = { position, width ->
                                tabPositions = tabPositions.toMutableList().apply {
                                    this[index] = TabPosition(position, width)
                                }
                                if (currentTab == tab) {
                                    selectedTabWidth = width
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ZiineTopBarItem(
    tab: MainTab,
    selected: Boolean,
    onSelected: () -> Unit,
    onPositioned: (position: Int, width: Int) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(37.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onSelected() }
            .onGloballyPositioned { coordinates ->
                onPositioned(
                    coordinates.positionInParent().x.toInt(),
                    coordinates.size.width
                )
            }
    ) {
        Text(
            text = tab.label,
            color = if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSurfaceVariant,
            style = Subtitle2,
            modifier = Modifier.padding(horizontal = 16.dp)
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
