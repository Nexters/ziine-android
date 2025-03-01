package com.nexters.ziine.android.presentation.magazine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.common.util.toPx
import com.nexters.ziine.android.presentation.common.util.toDp
import com.nexters.ziine.android.presentation.component.LoadingIndicator
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineUiAction
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineUiEvent
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineUiState
import com.nexters.ziine.android.presentation.magazine.viewModel.MagazineViewModel
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph5
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlin.math.absoluteValue

@Composable
internal fun MagazineRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToMagazineDetail: (Int) -> Unit,
    magazineViewModel: MagazineViewModel = hiltViewModel(),
) {
    val magazineUiState by magazineViewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = magazineViewModel.uiEvent) { event ->
        when (event) {
            is MagazineUiEvent.MoveToMagazineDetail -> navigateToMagazineDetail(event.magazineId)
        }
    }
    if (!magazineUiState.isLoading) {
        MagazineScreen(
            padding = padding,
            uiState = magazineUiState,
            modifier = modifier,
            onAction = magazineViewModel::onAction,
        )
    } else {
        LoadingIndicator(isLoading = true)
    }
}

@Composable
internal fun MagazineScreen(
    padding: PaddingValues,
    uiState: MagazineUiState,
    modifier: Modifier = Modifier,
    onAction: (MagazineUiAction) -> Unit,
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

    /** 인디케이터 */
    val indicatorPageNumber = pagerState.currentPage % actualPageCount

    Column(
        modifier = modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                ).toDp(),
            ),
            pageSpacing = 12.dp,
        ) { page ->
            val actualPageNumber = page % actualPageCount
            val pageData = uiState.getMagazine(actualPageNumber)
            MagazineItem(
                data = pageData,
                modifier = Modifier
                    .clickable { onAction(MagazineUiAction.MagazineClicked(pageData.magazineId)) }
                    .graphicsLayer { // todo: 현재 이미지 줄어드는 만큼 페이지간 간격도 넓어짐 보정 잡아야함(우선 넘어감)
                        val scaleFactor = PagerItemShrinker.scaleFactor(pagerState, page)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    },
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        MagazineIndicator(actualPageCount, indicatorPageNumber)
    }
}

@Composable
private fun MagazineIndicator(
    totalPageCount: Int,
    currentPageNumber: Int,
) {
    val uiCurrentPAgeCount = currentPageNumber + 1
    Text(
        modifier = Modifier
            .height(27.dp)
            .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(99.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(99.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .wrapContentSize(Alignment.Center),
        text = "$uiCurrentPAgeCount/$totalPageCount",
        color = MaterialTheme.colorScheme.primary,
        style = Paragraph5,
    )
}

@DevicePreview
@Composable
private fun MagazineScreenPreview() {
    ZiineTheme {
        MagazineScreen(padding = PaddingValues(), uiState = MagazineUiState(), onAction = {})
    }
}

@ComponentPreview
@Composable
private fun MagazineTagPreview() {
    ZiineTheme {
        MagazineIndicator(22, 1)
    }
}

private object PagerItemShrinker {
    fun contentPaddingToAlignCenter(
        screenWidth: Int,
        pageItemWidth: Int,
    ): Int = (screenWidth - pageItemWidth) / 2

    fun scaleFactor(
        pagerState: PagerState,
        page: Int,
    ): Float {
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        val scaleFactor = lerp(
            start = 0.9164f,
            stop = 1f,
            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
        )
        return scaleFactor
    }
}
