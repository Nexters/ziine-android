package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.LocalSharedTransitionScope
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Paragraph2
import com.nexters.ziine.android.presentation.ui.theme.Paragraph3
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ArtworkDescription(
    artworkDetail: UiArtworkDetail,
    onShareClick: (String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    with(sharedTransitionScope) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.background),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = artworkDetail.title,
                    modifier = Modifier
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = artworkDetail.title),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .weight(1f),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = Heading4,
                )
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = { onShareClick(artworkDetail.shareUrl) },
                    modifier = Modifier.then(Modifier.size(32.dp))
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_share),
                        contentDescription = "Share Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .height(27.dp)
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(100.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.artwork_size),
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = MaterialTheme.colorScheme.primary,
                        style = Paragraph4,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "${artworkDetail.width}cm",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = Paragraph3,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_multiply),
                        contentDescription = "Dot Icon",
                        tint = Color.Unspecified,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${artworkDetail.height}cm",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = Paragraph3,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .height(27.dp)
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(100.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.artwork_material),
                        modifier = Modifier.padding(horizontal = 25.dp),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = Paragraph4,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    Text(
                        text = artworkDetail.material,
                        color = MaterialTheme.colorScheme.onSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = Paragraph3,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = artworkDetail.description,
                    style = Paragraph2,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ComponentPreview
@Composable
private fun ArtworkDescriptionPreview() {
    ZiineTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                ) {
                    ArtworkDescription(
                        artworkDetail = UiArtworkDetail(
                            id = 0,
                            title = "작품 제목 작품 제목 작품 제목 작품 제목 작품 제목 작품 제목",
                            width = 120,
                            height = 120,
                            material = "캔버스에 유화 캔버스에 유화 캔버스에 유화 캔버스에 유화 캔버스에 유화 캔버스에 유화",
                            description = "Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text",
                        ),
                        onShareClick = {},
                        animatedVisibilityScope = this@AnimatedVisibility,
                    )
                }
            }
        }
    }
}
