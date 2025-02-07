package com.nexters.ziine.android.presentation.registerArtwork.pending

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.registerArtwork.pending.model.UiPendingGuideItem
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Paragraph3
import com.nexters.ziine.android.presentation.ui.theme.Subtitle1
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import java.util.Locale

@Composable
internal fun PendingRoute(modifier: Modifier = Modifier, activityFinishAction: () -> Unit) {
    PendingScreen(
        modifier = modifier,
        activityFinishAction
    )
}

@Composable
internal fun PendingScreen(modifier: Modifier = Modifier, activityFinishAction: () -> Unit) {
    val scrollState = rememberScrollState()
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(scrollState.value) { isScrolled = scrollState.value > 0 }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        RegisterTopBar(isScrolled = isScrolled) { activityFinishAction() }
        GuideUI(scrollState)
    }
}

@Composable
private fun GuideUI(scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GuideMainTitle()
        GuideContent(getPendingGuideItems())
    }
}

@Composable
private fun GuideMainTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register_artwork_main_title_first),
            color = MaterialTheme.colorScheme.onBackground,
            style = Heading4

        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.register_artwork_main_title_second),
            color = MaterialTheme.colorScheme.onBackground,
            style = Heading4
        )
    }
}

@Composable
private fun GuideContent(pendingGuideItems: List<UiPendingGuideItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        pendingGuideItems.forEachIndexed { index, item ->
            val order = index + 1
            val title = item.title
            val content = item.content
            val imageId = item.imageId
            GuideContentGeneralForm(order, title, content, imageId)
            if (index != pendingGuideItems.lastIndex) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun getPendingGuideItems(): List<UiPendingGuideItem> =
    listOf(
        UiPendingGuideItem(
            title = stringResource(R.string.register_artwork_first_content_title),
            content = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.register_artwork_first_content_content_first))
                }
                append(stringResource(R.string.register_artwork_first_content_content_second))
            },
            imageId = R.drawable.ic_arrow_back
        ),
        UiPendingGuideItem(
            title = stringResource(R.string.register_artwork_second_content_title),
            content = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.register_artwork_second_content_content_first))
                }
                append(stringResource(R.string.register_artwork_second_content_content_second))
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.register_artwork_second_content_content_third))
                }
                append(stringResource(R.string.register_artwork_second_content_content_fourth))
            },
            imageId = R.drawable.ic_arrow_back
        ),
        UiPendingGuideItem(
            title = stringResource(R.string.register_artwork_third_content_title),
            content = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.register_artwork_thirdcontent_content_first))
                }
            },
            imageId = R.drawable.ic_arrow_back
        )
    )

@Composable
private fun GuideContentGeneralForm(
    order: Int,
    title: String,
    content: AnnotatedString,
    @DrawableRes imageId: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = String.format(Locale.KOREA, "%02d", order),
            color = MaterialTheme.colorScheme.onBackground,
            style = Subtitle1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = Subtitle1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            color = MaterialTheme.colorScheme.onBackground,
            style = Paragraph3
        )
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.5.dp)
                .aspectRatio(320f / 240f),
            painter = painterResource(id = imageId),
            contentDescription = null
        )
    }
}

@DevicePreview
@Composable
private fun PendingScreenPreview() {
    ZiineTheme {
        PendingScreen(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            activityFinishAction = {})
    }
}
