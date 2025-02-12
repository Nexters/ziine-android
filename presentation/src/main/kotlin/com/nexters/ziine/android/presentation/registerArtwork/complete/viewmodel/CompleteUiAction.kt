package com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel

import com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel.PendingUiAction

interface CompleteUiAction {
    object OnMoveToHomeButtonClicked : CompleteUiAction
    object OnBackButtonClicked : CompleteUiAction
}
