package com.nexters.ziine.android.presentation.artworks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.LocalNavAnimatedVisibilityScope
import com.nexters.ziine.android.presentation.LocalSharedTransitionScope
import com.nexters.ziine.android.presentation.artworks.model.UiArtist
import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import com.nexters.ziine.android.presentation.component.NetworkImage
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Gray0
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Paragraph2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

// TODO title 영역 그라이언트 박스 깔아야함, 테두리도 적용해야함
// TODO NetworkImage 실패 이미지 처리
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ArtworkItem(
    artwork: UiArtwork,
    onArtworkItemSelect: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .sharedElement(
                    rememberSharedContentState(key = artwork.imageUrl),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(max = 900.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onArtworkItemSelect),
        ) {
            NetworkImage(
                imageUrl = artwork.imageUrl,
                contentDescription = "${artwork.title} by ${artwork.artist.name}",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp)
                    .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NetworkImage(
                    imageUrl = artwork.artist.profileImageUrl,
                    contentDescription = "Profile Image of ${artwork.artist.name}",
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = artwork.artist.name,
                    style = Paragraph2,
                    color = Gray0,
                    modifier = Modifier.weight(1f),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = artwork.title,
                    modifier = Modifier
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = artwork.title),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    color = Gray0,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Heading4,
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ComponentPreview
@Composable
private fun ArtworkItemPreview() {
    ZiineTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this@AnimatedVisibility,
                ) {
                    ArtworkItem(
                        artwork = UiArtwork(
                            id = 1,
                            imageUrl = "",
                            artist = UiArtist(
                                id = 1,
                                name = "Artist Name",
                                profileImageUrl = "",
                            ),
                            title = "Artwork Name",
                        ),
                        onArtworkItemSelect = {},
                        animatedVisibilityScope = this@AnimatedVisibility,
                    )
                }
            }
        }
    }
}
