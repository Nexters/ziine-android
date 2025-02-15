package com.nexters.ziine.android.presentation.magazine.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nexters.ziine.android.domain.repository.MagazineRepository
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiAction
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiEvent
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import com.nexters.ziine.android.presentation.magazine.model.UiMagazine
import com.nexters.ziine.android.presentation.mapper.magazine.toUiMagazines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.list
import javax.inject.Inject

@HiltViewModel
class MagazineViewModel @Inject constructor(
    private val magazineRepository: MagazineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MagazineUiState())
    val uiState: StateFlow<MagazineUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MagazineUiEvent>()
    val uiEvent: Flow<MagazineUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MagazineUiAction) {
        when (action) {
            is MagazineUiAction.MagazineClicked -> moveToMagazineDetail(action.magazineId)
        }
    }

    init {
        fetchMagazines()
    }

    private fun fetchMagazines() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            magazineRepository.fetchMagazines().onSuccess { magazines ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        magazines = magazines.toUiMagazines()
                    )
                }
            }
        }
    }

    private fun moveToMagazineDetail(magazineId: Int) {
        viewModelScope.launch {
            _uiEvent.send(MagazineUiEvent.MoveToMagazineDetail(magazineId))
        }
    }
}
