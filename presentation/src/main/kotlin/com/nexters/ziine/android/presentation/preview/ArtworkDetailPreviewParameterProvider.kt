package com.nexters.ziine.android.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtistDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiState

internal class ArtworkDetailPreviewParameterProvider : PreviewParameterProvider<ArtworkDetailUiState> {
    override val values = sequenceOf(
        ArtworkDetailUiState(
            artworkDetail = UiArtworkDetail(
                id = 1,
                title = "Artwork 1",
                width = 100,
                height = 100,
                material = "Material 1",
                description = "Description 1",
                artworkImageUrl = "https://placehold.co/600x400/png",
                artist = UiArtistDetail(
                    id = 1,
                    name = "Artist 1",
                    profileImageUrl = "https://example.com/profile1.png",
                ),
            ),
            url = "https://m.naver.com",
        ),
    )
}
