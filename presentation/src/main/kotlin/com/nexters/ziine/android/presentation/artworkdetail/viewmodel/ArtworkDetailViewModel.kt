package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nexters.ziine.android.domain.repository.ArtworkRepository
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtistDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiContact
import com.nexters.ziine.android.presentation.artworkdetail.model.UiExhibition
import com.nexters.ziine.android.presentation.mapper.artwork.toUiArtworkDetail
import com.nexters.ziine.android.presentation.navigation.Route
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
class ArtworkDetailViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val id = savedStateHandle.toRoute<Route.ArtworkDetail>().id
    private val title = savedStateHandle.toRoute<Route.ArtworkDetail>().title
    private val artworkImageUrl = savedStateHandle.toRoute<Route.ArtworkDetail>().artworkImageUrl

    private val _uiState = MutableStateFlow(ArtworkDetailUiState())
    val uiState: StateFlow<ArtworkDetailUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<ArtworkDetailUiEvent>()
    val uiEvent: Flow<ArtworkDetailUiEvent> = _uiEvent.receiveAsFlow()

    init {
        fetchArtworkDetail(id)
    }

    fun onAction(action: ArtworkDetailUiAction) {
        when (action) {
            is ArtworkDetailUiAction.OnBackClick -> navigateBack()
            is ArtworkDetailUiAction.OnShareClick -> shareUrl(action.url)
            is ArtworkDetailUiAction.OnCopyClick -> copyValue(action.type, action.value)
        }
    }

    private fun fetchArtworkDetail(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            artworkRepository.fetchArtworkDetail(id)
                .onSuccess { result ->
                    val uiArtworkDetail = result.toUiArtworkDetail()
                    _uiState.update {
                        it.copy(
                            artworkDetail = UiArtworkDetail(
                                id = id,
                                title = title,
                                width = uiArtworkDetail.width,
                                height = uiArtworkDetail.height,
                                material = uiArtworkDetail.material,
                                description = uiArtworkDetail.description,
                                artworkImageUrl = artworkImageUrl,
                                artist = UiArtistDetail(
                                    id = uiArtworkDetail.artist.id,
                                    name = uiArtworkDetail.artist.name,
                                    profileImageUrl = uiArtworkDetail.artist.profileImageUrl,
                                    education = uiArtworkDetail.artist.education.toImmutableList(),
                                    exhibition = uiArtworkDetail.artist.exhibition.map { exhibition ->
                                        UiExhibition(
                                            title = exhibition.title,
                                            exhibitionDate = exhibition.exhibitionDate,
                                        )
                                    }.toImmutableList(),
                                    contact = uiArtworkDetail.artist.contact.map { contact ->
                                        UiContact(
                                            type = contact.type,
                                            value = contact.value,
                                        )
                                    }.toImmutableList(),
                                    email = uiArtworkDetail.artist.email,
                                ),
                            ),
                            url = "https://m.naver.com",
                        )
                    }
                }.onFailure {
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(ArtworkDetailUiEvent.NavigateBack)
        }
    }

    private fun shareUrl(url: String) {
        viewModelScope.launch {
            _uiEvent.send(ArtworkDetailUiEvent.ShareUrl(url))
        }
    }

    private fun copyValue(
        type: String,
        value: String,
    ) {
        viewModelScope.launch {
            _uiEvent.send(ArtworkDetailUiEvent.CopyValue(type, value))
        }
    }
}
