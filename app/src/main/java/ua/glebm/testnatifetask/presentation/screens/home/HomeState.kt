package ua.glebm.testnatifetask.presentation.screens.home

import androidx.paging.PagingData
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

data class HomeState(
    val pagingGifs: PagingData<Gif>? = null,
)
