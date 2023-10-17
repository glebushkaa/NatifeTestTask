package ua.glebm.testnatifetask.presentation.screens.home

import androidx.paging.PagingData
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

sealed class HomeSideEffect {

    data class GifsListUpdate(
        val pagedGifs: PagingData<Gif>,
    ) : HomeSideEffect()
}
