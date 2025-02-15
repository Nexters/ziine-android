package com.nexters.ziine.android.presentation.magazine

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.common.util.toPx
import com.nexters.ziine.android.presentation.common.util.tooDp
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineUiState
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineViewModel
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlin.math.absoluteValue

@Composable
internal fun MagazineRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    magazineViewModel: MagazineViewModel = hiltViewModel(),
) {
    val magazineUiState by magazineViewModel.uiState.collectAsStateWithLifecycle()

    MagazineScreen(
        padding = padding,
        uiState = magazineUiState,
        modifier = modifier,
    )
}

@Composable
internal fun MagazineScreen(
    padding: PaddingValues,
    uiState: MagazineUiState,
    modifier: Modifier = Modifier,
) {
    /** 페이지 스케일 조정 */
    val context = LocalContext.current
    val screenWidth = remember { context.resources.displayMetrics.widthPixels }
    val magazineItemWidth = MAGAZINE_ITEM_WIDTH.dp.toPx()

    /** 무한 페이저 */
    val actualPageCount = uiState.magazines.size
    val pageCount = Int.MAX_VALUE
    val maxNumOfRounds = Int.MAX_VALUE / actualPageCount
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = (maxNumOfRounds / 2) * actualPageCount,
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
                horizontal = PagerItemShrinker.contentPaddingToAlignCenter(
                    screenWidth = screenWidth,
                    pageItemWidth = magazineItemWidth,
                ).tooDp(),
            ),
            pageSpacing = 12.dp,
            /** 대략 줄여놓은것 하단 todo와 동시 처리 필요 */
        ) { page ->
            val actualPageNumber = page % actualPageCount
            MagazineItem(
                data = uiState.magazines[actualPageNumber],
                modifier = Modifier
                    .graphicsLayer {
                        val scaleFactor = PagerItemShrinker.scaleFactor(pagerState, page)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }, // todo: 현재 이미지 줄어드는 만큼 페이지간 간격도 넓어짐 보정 잡아야함(우선 넘어감)
            )
        }
    }
}

@DevicePreview
@Composable
private fun MagazineScreenPreview() {
    ZiineTheme {
        MagazineScreen(padding = PaddingValues(), uiState = MagazineUiState())
    }
}

private object PagerItemShrinker {
    fun contentPaddingToAlignCenter(screenWidth: Int, pageItemWidth: Int): Int = (screenWidth - pageItemWidth) / 2

    fun scaleFactor(pagerState: PagerState, page: Int): Float {
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        val scaleFactor = lerp(
            start = 0.9164f,
            stop = 1f,
            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
        )
        return scaleFactor
    }
}
