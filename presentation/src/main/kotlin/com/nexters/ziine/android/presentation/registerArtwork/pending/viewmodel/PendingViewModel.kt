package com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PendingViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiEvent = Channel<PendingUiEvent>()
        val uiEvent: Flow<PendingUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: PendingUiAction) {
            when (action) {
                is PendingUiAction.OnMoveToRegisterButtonClicked -> navigateToRegister()
                is PendingUiAction.OnBackButtonClicked -> finishActivity()
            }
        }

        private fun navigateToRegister() {
            viewModelScope.launch {
                _uiEvent.send(PendingUiEvent.NavigateToRegister)
            }
        }

        private fun finishActivity() {
            viewModelScope.launch {
                _uiEvent.send(PendingUiEvent.FinishActivity)
            }
        }
    }
