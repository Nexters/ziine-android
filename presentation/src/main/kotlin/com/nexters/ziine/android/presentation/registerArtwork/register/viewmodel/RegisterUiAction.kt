package com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel

sealed interface RegisterUiAction {
    object OnMoveToCompleteButtonClicked : RegisterUiAction

    object OnBackButtonClicked : RegisterUiAction
}
