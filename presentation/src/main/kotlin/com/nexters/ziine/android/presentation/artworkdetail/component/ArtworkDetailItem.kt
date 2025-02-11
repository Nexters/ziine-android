package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.LocalSharedTransitionScope
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworks.ArtworkItem
import com.nexters.ziine.android.presentation.artworks.model.UiArtist
import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import com.nexters.ziine.android.presentation.component.NetworkImage
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ArtworkDetailItem(
    artwork: UiArtworkDetail,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .sharedElement(
                    rememberSharedContentState(key = artwork.artworkImageUrl),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .fillMaxWidth()
                .heightIn(max = 900.dp),
        ) {
            NetworkImage(
                imageUrl = artwork.artworkImageUrl,
                contentDescription = "${artwork.title} by ${artwork.artist.name}",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
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
                ) {
                    ArtworkItem(
                        artwork = UiArtwork(
                            id = 1,
                            artworkImageUrl = "",
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
