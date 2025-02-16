package com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiEvent = Channel<RegisterUiEvent>()
        val uiEvent: Flow<RegisterUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: RegisterUiAction) {
            when (action) {
                is RegisterUiAction.OnMoveToCompleteButtonClicked -> navigateToComplete()
                is RegisterUiAction.OnBackButtonClicked -> backToPrevious()
            }
        }

        private fun navigateToComplete() {
            viewModelScope.launch {
                _uiEvent.send(RegisterUiEvent.NavigateToComplete)
            }
        }

        private fun backToPrevious() {
            viewModelScope.launch {
                _uiEvent.send(RegisterUiEvent.BackToPrevious)
            }
        }
    }
