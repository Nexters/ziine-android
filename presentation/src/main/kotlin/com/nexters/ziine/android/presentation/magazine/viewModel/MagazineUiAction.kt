package com.nexters.ziine.android.presentation.magazine.viewModel

sealed interface MagazineUiAction {
    data class OnMagazineClicked(val magazineId: Int) : MagazineUiAction

    object OnRetryClicked : MagazineUiAction
}
