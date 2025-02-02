package com.nexters.ziine.android.presentation.artworks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.ziine.android.presentation.artworks.model.Artwork
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
