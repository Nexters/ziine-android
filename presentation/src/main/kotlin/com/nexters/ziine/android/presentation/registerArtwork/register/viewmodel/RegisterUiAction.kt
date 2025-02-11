package com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel

import com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel.PendingUiAction

interface RegisterUiAction {
    object OnMoveToCompleteButtonClicked : RegisterUiAction
    object OnBackButtonClicked: RegisterUiAction
}
