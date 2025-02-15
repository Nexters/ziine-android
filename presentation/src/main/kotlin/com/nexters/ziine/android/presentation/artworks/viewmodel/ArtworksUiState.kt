package com.nexters.ziine.android.presentation.artworks.viewmodel

import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ArtworksUiState(
    val isLoading: Boolean = false,
    val artworks: ImmutableList<UiArtwork> = persistentListOf(),
    val isError: Boolean = false,
)
