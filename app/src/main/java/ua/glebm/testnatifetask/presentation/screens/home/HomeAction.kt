package ua.glebm.testnatifetask.presentation.screens.home

import androidx.paging.PagingData
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

sealed class HomeAction {

    data class UpdateSearchQuery(
        val query: String,
    ) : HomeAction()

    data class UpdateGifsList(
        val pagedGifs: PagingData<Gif>,
    ) : HomeAction()

    data class NavigateToFullscreen(
        val uniqueId: String,
        val position: Int,
    ) : HomeAction()

    data class RemoveGif(
        val uniqueId: String,
    ) : HomeAction()

    inline fun handle(
        updateSearchQuery: (String) -> Unit = {},
        updateGifsList: (PagingData<Gif>) -> Unit = {},
        navigateToFullscreen: (String, Int) -> Unit = { _, _ -> },
        removeGif: (String) -> Unit = {},
    ) {
        when (this) {
            is UpdateSearchQuery -> updateSearchQuery(query)
            is UpdateGifsList -> updateGifsList(pagedGifs)
            is NavigateToFullscreen -> navigateToFullscreen(uniqueId, position)
            is RemoveGif -> removeGif(uniqueId)
        }
    }
}
