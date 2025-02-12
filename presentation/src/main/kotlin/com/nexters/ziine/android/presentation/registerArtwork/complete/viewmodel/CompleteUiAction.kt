package com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel

sealed interface CompleteUiAction {
    object OnMoveToHomeButtonClicked : CompleteUiAction

    object OnBackButtonClicked : CompleteUiAction
}
