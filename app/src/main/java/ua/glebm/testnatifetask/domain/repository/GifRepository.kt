package ua.glebm.testnatifetask.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

interface GifRepository {

    suspend fun removeGif(uniqueId: String)

    fun getSearchingGifs(query: String): Flow<PagingData<Gif>>
    suspend fun getSearchingGifsFromItem(
        query: String,
        uniqueId: String,
    ): Flow<PagingData<Gif>>
}
