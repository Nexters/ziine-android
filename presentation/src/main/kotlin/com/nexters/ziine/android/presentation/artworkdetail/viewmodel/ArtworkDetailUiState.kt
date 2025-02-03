package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail

data class ArtworkDetailUiState(
    val isLoading: Boolean = false,
    val artwork: UiArtworkDetail? = null,
)
