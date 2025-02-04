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
                    artist = UiArtist(
                        id = 1,
                        name = "Artist 1",
                        profileImageUrl = "https://example.com/profile1.png",
                    ),
                    imageUrl = "https://placehold.co/600x400/png",
                    title = "Artwork 1",
                ),
                UiArtwork(
                    id = 2,
                    artist = UiArtist(
                        id = 2,
                        name = "Artist 2",
                        profileImageUrl = "https://example.com/profile2.png",
                    ),
                    imageUrl = "https://placehold.co/400x600/png",
                    title = "Artwork 2",
                ),
                UiArtwork(
                    id = 3,
                    artist = UiArtist(
                        id = 3,
                        name = "Artist 3",
                        profileImageUrl = "https://example.com/profile3.png",
                    ),
                    imageUrl = "https://placehold.co/500x500/png",
                    title = "Artwork 3",
                ),
                UiArtwork(
                    id = 4,
                    artist = UiArtist(
                        id = 4,
                        name = "Artist 4",
                        profileImageUrl = "https://example.com/profile4.png",
                    ),
                    imageUrl = "https://placehold.co/300x500/png",
                    title = "Artwork 4",
                ),
                UiArtwork(
                    id = 5,
                    artist = UiArtist(
                        id = 5,
                        name = "Artist 5",
                        profileImageUrl = "https://example.com/profile5.png",
                    ),
                    imageUrl = "https://placehold.co/500x300/png",
                    title = "Artwork 5",
                ),
                UiArtwork(
                    id = 6,
                    artist = UiArtist(
                        id = 6,
                        name = "Artist 6",
                        profileImageUrl = "https://example.com/profile1.png",
                    ),
                    imageUrl = "https://placehold.co/400x800/png",
                    title = "Artwork 6",
                ),
            ),
        ),
    )
}
