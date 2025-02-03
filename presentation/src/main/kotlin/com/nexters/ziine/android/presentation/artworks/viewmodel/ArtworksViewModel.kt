package com.nexters.ziine.android.presentation.artworks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworksViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiState = MutableStateFlow(ArtworksUiState())
        val uiState: StateFlow<ArtworksUiState> = _uiState.asStateFlow()

        private val _uiEvent = Channel<ArtworksUiEvent>()
        val uiEvent: Flow<ArtworksUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: ArtworksUiAction) {
            when (action) {
                is ArtworksUiAction.OnArtworkItemSelect -> navigateToArtworkDetail(action.artworkId)
            }
        }

        init {
            getAlbumList()
        }

        private fun getAlbumList() {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        artworks = persistentListOf(
                            UiArtwork(
                                id = 1,
                                artistName = "Artist 1",
                                imageUrl = "https://placehold.co/600x400/png",
                                title = "Artwork 1",
                            ),
                            UiArtwork(
                                id = 2,
                                artistName = "Artist 2",
                                imageUrl = "https://placehold.co/400x600/png",
                                title = "Artwork 2",
                            ),
                            UiArtwork(
                                id = 3,
                                artistName = "Artist 3",
                                imageUrl = "https://placehold.co/500x500/png",
                                title = "Artwork 3",
                            ),
                            UiArtwork(
                                id = 4,
                                artistName = "Artist 4",
                                imageUrl = "https://placehold.co/300x500/png",
                                title = "Artwork 4",
                            ),
                            UiArtwork(
                                id = 5,
                                artistName = "Artist 5",
                                imageUrl = "https://placehold.co/500x300/png",
                                title = "Artwork 5",
                            ),
                            UiArtwork(
                                id = 6,
                                artistName = "Artist 6",
                                imageUrl = "https://placehold.co/400x800/png",
                                title = "Artwork 6",
                            ),
                        ),
                    )
                }
            }
        }

        private fun navigateToArtworkDetail(artworkId: Int) {
            viewModelScope.launch {
                _uiEvent.send(ArtworksUiEvent.NavigateToArtworkDetail(artworkId))
            }
        }
    }
