package com.nexters.ziine.android.presentation.artworks.viewmodel

import com.nexters.ziine.android.presentation.artworks.model.Artwork
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ArtworksUiState(
    val isLoading: Boolean = false,
    val artworks: ImmutableList<Artwork> = persistentListOf(),
)
