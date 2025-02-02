package com.nexters.ziine.android.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nexters.ziine.android.presentation.artworks.model.Artwork
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import kotlinx.collections.immutable.persistentListOf

internal class ArtworksPreviewParameterProvider : PreviewParameterProvider<ArtworksUiState> {
    override val values = sequenceOf(
        ArtworksUiState(
            artworks = persistentListOf(
                Artwork(
                    id = 1,
                    artistName = "Artist 1",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 1",
                ),
                Artwork(
                    id = 2,
                    artistName = "Artist 2",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 2",
                ),
                Artwork(
                    id = 3,
                    artistName = "Artist 3",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 3",
                ),
                Artwork(
                    id = 4,
                    artistName = "Artist 4",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 4",
                ),
                Artwork(
                    id = 5,
                    artistName = "Artist 5",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 5",
                ),
                Artwork(
                    id = 6,
                    artistName = "Artist 6",
                    imageUrl = "https://via.placeholder.com/150",
                    title = "Artwork 6",
                ),
            ),
        ),
    )
}
