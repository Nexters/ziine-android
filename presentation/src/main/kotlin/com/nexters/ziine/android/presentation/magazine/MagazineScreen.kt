package com.nexters.ziine.android.presentation.magazine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.nexters.ziine.android.presentation.common.util.toPx
import com.nexters.ziine.android.presentation.common.util.tooDp
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlin.math.absoluteValue

@Composable
internal fun MagazineRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    MagazineScreen(
        padding = padding,
        modifier = modifier,
    )
}

@Composable
internal fun MagazineScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val screenWidth = remember { context.resources.displayMetrics.widthPixels }
    val magazineItemWidth = MAGAZINE_ITEM_WIDTH.dp.toPx()

    val pagerState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
    )

    Column(
        modifier = modifier
            .padding(padding)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            state = pagerState,
            contentPadding = PaddingValues(
                horizontal = getContentPaddingToAlignCenter(
                    screenWidth = screenWidth,
                    pageItemWidth = magazineItemWidth,
                ).tooDp(),
            ),
            pageSpacing = 16.dp,
        ) { page ->
            MagazineItem(
                modifier = Modifier
                    .graphicsLayer {
                        val scaleFactor = getScaleFactor(pagerState, page)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    },
            )
        }
    }
}

private fun getContentPaddingToAlignCenter(screenWidth: Int, pageItemWidth: Int): Int = (screenWidth - pageItemWidth) / 2

private fun getScaleFactor(pagerState: PagerState, page: Int): Float {
    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
    val scaleFactor = lerp(
        start = 0.9164f,
        stop = 1f,
        fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
    )
    return scaleFactor
}

@DevicePreview
@Composable
private fun MagazineScreenPreview() {
    ZiineTheme {
        MagazineScreen(padding = PaddingValues())
    }
}
