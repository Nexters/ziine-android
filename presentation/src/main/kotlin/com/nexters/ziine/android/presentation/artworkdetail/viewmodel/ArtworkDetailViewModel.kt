package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
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
class ArtworkDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val id = savedStateHandle.toRoute<Route.ArtworkDetail>().id
        private val artistName = savedStateHandle.toRoute<Route.ArtworkDetail>().artistName
        private val imageUrl = savedStateHandle.toRoute<Route.ArtworkDetail>().imageUrl
        private val title = savedStateHandle.toRoute<Route.ArtworkDetail>().title

        private val _uiState = MutableStateFlow(ArtworkDetailUiState())
        val uiState: StateFlow<ArtworkDetailUiState> = _uiState.asStateFlow()

        private val _uiEvent = Channel<ArtworkDetailUiEvent>()
        val uiEvent: Flow<ArtworkDetailUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: ArtworkDetailUiAction) {
        }

        init {
            fetchArtworkDetail()
        }

        private fun fetchArtworkDetail() {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        artwork = UiArtworkDetail(
                            id = id,
                            artistName = artistName,
                            imageUrl = imageUrl,
                            title = title,
                        )
                    )
                }
            }
        }
    }
