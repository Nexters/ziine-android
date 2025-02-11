package com.nexters.ziine.android.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nexters.ziine.android.presentation.artworks.model.UiArtist
import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import kotlinx.collections.immutable.persistentListOf

internal class ArtworksPreviewParameterProvider : PreviewParameterProvider<ArtworksUiState> {
    override val values = sequenceOf(
        ArtworksUiState(
            artworks = persistentListOf(
                UiArtwork(
                    id = 1,
                    title = "Artwork 1",
                    artworkImageUrl = "https://placehold.co/600x400/png",
                    artist = UiArtist(
                        id = 1,
                        name = "Artist 1",
                        profileImageUrl = "https://example.com/profile1.png",
                    ),
                ),
                UiArtwork(
                    id = 2,
                    title = "Artwork 2",
                    artworkImageUrl = "https://placehold.co/400x600/png",
                    artist = UiArtist(
                        id = 2,
                        name = "Artist 2",
                        profileImageUrl = "https://example.com/profile2.png",
                    ),
                ),
                UiArtwork(
                    id = 3,
                    title = "Artwork 3",
                    artworkImageUrl = "https://placehold.co/500x500/png",
                    artist = UiArtist(
                        id = 3,
                        name = "Artist 3",
                        profileImageUrl = "https://example.com/profile3.png",
                    ),
                ),
                UiArtwork(
                    id = 4,
                    title = "Artwork 4",
                    artworkImageUrl = "https://placehold.co/300x500/png",
                    artist = UiArtist(
                        id = 4,
                        name = "Artist 4",
                        profileImageUrl = "https://example.com/profile4.png",
                    ),
                ),
                UiArtwork(
                    id = 5,
                    title = "Artwork 5",
                    artworkImageUrl = "https://placehold.co/500x300/png",
                    artist = UiArtist(
                        id = 5,
                        name = "Artist 5",
                        profileImageUrl = "https://example.com/profile5.png",
                    ),
                ),
                UiArtwork(
                    id = 6,
                    title = "Artwork 6",
                    artworkImageUrl = "https://placehold.co/400x800/png",
                    artist = UiArtist(
                        id = 6,
                        name = "Artist 6",
                        profileImageUrl = "https://example.com/profile1.png",
                    ),
                ),
            ),
        ),
    )
}
