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

    inline fun handle(
        updateSearchQuery: (String) -> Unit = {},
        updateGifsList: (PagingData<Gif>) -> Unit = {},
    ) {
        when (this) {
            is UpdateSearchQuery -> updateSearchQuery(query)
            is UpdateGifsList -> updateGifsList(pagedGifs)
        }
    }
}
