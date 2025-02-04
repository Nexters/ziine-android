package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nexters.ziine.android.presentation.artworkdetail.model.UiContact
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtistDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiExhibition
import com.nexters.ziine.android.presentation.navigation.Route
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
class ArtworkDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val id = savedStateHandle.toRoute<Route.ArtworkDetail>().id
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
                            title = title,
                            imageUrl = imageUrl,
                            width = 100,
                            height = 100,
                            material = "페인트",
                            description = "진짜 진짜 열심히 그림(진짜임)",
                            artist = UiArtistDetail(
                                id = 1,
                                name = "멧돼지",
                                profileImageUrl = "https://example.com/profile1/png",
                                education = persistentListOf("세종대학교", "동양학과"),
                                exhibition = persistentListOf(
                                    UiExhibition(
                                        title = "세종대학교 졸업전시회",
                                        exhibitionDate = "2025.02.01",
                                    ),
                                ),
                                contact = persistentListOf(
                                    UiContact(
                                        type = "INSTAGRAM",
                                        value = "y_joo_z",
                                    ),
                                ),
                                email = "yjoo@ziine.com",
                            ),
                        ),
                    )
                }
            }
        }
    }
