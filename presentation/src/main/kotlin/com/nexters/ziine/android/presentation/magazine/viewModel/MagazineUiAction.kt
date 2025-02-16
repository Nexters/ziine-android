package com.nexters.ziine.android.presentation.magazine.viewModel

sealed interface MagazineUiAction {
    data class MagazineClicked(val magazineId: Int) : MagazineUiAction
}
