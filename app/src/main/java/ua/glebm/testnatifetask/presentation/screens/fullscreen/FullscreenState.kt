package ua.glebm.testnatifetask.presentation.screens.fullscreen

import androidx.paging.PagingData
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

data class FullscreenState(
    val pagingGifs: PagingData<Gif>? = null,
)
