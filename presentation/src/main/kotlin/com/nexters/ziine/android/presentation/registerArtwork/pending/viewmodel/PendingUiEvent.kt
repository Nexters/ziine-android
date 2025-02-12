package com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel

sealed interface PendingUiEvent {
    object NavigateToRegister : PendingUiEvent

    object FinishActivity : PendingUiEvent
}
