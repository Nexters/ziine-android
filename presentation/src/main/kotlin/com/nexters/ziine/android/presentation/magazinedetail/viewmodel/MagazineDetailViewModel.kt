package com.nexters.ziine.android.presentation.magazinedetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nexters.ziine.android.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MagazineDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val magazineId = savedStateHandle.toRoute<Route.MagazineDetail>().magazineId

    private val _uiEvent = Channel<MagazineDetailUiEvent>()
    val uiEvent: Flow<MagazineDetailUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MagazineDetailUiAction) {
        when (action) {
            is MagazineDetailUiAction.OnBackButtonClicked -> backToPrevious()
        }
    }

    private fun backToPrevious() {
        viewModelScope.launch {
            _uiEvent.send(MagazineDetailUiEvent.BackToPrevious)
        }
    }
}
