package com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiEvent = Channel<CompleteUiEvent>()
        val uiEvent: Flow<CompleteUiEvent> = _uiEvent.receiveAsFlow()

        fun onAction(action: CompleteUiAction) {
            when (action) {
                is CompleteUiAction.OnMoveToHomeButtonClicked -> finishActivity()
                is CompleteUiAction.OnBackButtonClicked -> finishActivity()
            }
        }

        private fun finishActivity() {
            viewModelScope.launch {
                _uiEvent.send(CompleteUiEvent.FinishActivity)
            }
        }
    }
