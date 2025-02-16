package com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel

sealed interface RegisterUiEvent {
    object NavigateToComplete : RegisterUiEvent

    object BackToPrevious : RegisterUiEvent
}
