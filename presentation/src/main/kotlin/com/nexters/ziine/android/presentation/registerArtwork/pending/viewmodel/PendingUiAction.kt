package com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel

sealed interface PendingUiAction {
    object OnMoveToRegisterButtonClicked : PendingUiAction

    object OnBackButtonClicked : PendingUiAction
}
