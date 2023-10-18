package ua.glebm.testnatifetask.presentation.screens.fullscreen

import androidx.paging.PagingData
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

sealed class FullscreenAction {

    data class UpdateGifsList(
        val pagedGifs: PagingData<Gif>,
    ) : FullscreenAction()

    inline fun handle(
        updateGifsList: (PagingData<Gif>) -> Unit = {},
    ) {
        when (this) {
            is UpdateGifsList -> updateGifsList(pagedGifs)
        }
    }
}
