package com.nexters.ziine.android.presentation.artworks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.ziine.android.domain.repository.ArtworkRepository
import com.nexters.ziine.android.presentation.mapper.artwork.toUiArtworks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
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
    constructor(
        private val artworkRepository: ArtworkRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(ArtworksUiState())
        val uiState: StateFlow<ArtworksUiState> = _uiState.asStateFlow()

        private val _uiEvent = Channel<ArtworksUiEvent>()
        val uiEvent: Flow<ArtworksUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: ArtworksUiAction) {
            when (action) {
                is ArtworksUiAction.OnArtworkItemSelect -> navigateToArtworkDetail(action.id, action.imageUrl, action.title)
            }
        }

        init {
            fetchArtworks()
        }

        private fun fetchArtworks() {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                artworkRepository.fetchArtworks()
                    .onSuccess { result ->
                        _uiState.update {
                            it.copy(artworks = result.map { it.toUiArtworks() }.toImmutableList())
                        }
                    }.onFailure {
                    }
                _uiState.update { it.copy(isLoading = false) }
            }
        }

        private fun navigateToArtworkDetail(
            id: Int,
            imageUrl: String,
            title: String,
        ) {
            viewModelScope.launch {
                _uiEvent.send(ArtworksUiEvent.NavigateToArtworkDetail(id, imageUrl, title))
            }
        }
    }
