package ua.glebm.testnatifetask.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.testnatifetask.data.database.GiphyDao
import ua.glebm.testnatifetask.data.database.entities.GifEntity
import ua.glebm.testnatifetask.data.mapper.toGifEntity
import ua.glebm.testnatifetask.data.network.GiphyApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@OptIn(ExperimentalPagingApi::class)
class GiphyPagingMediator(
    private val giphyDao: GiphyDao,
    private val giphyApi: GiphyApi,
    private val query: String,
) : RemoteMediator<Int, GifEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>,
    ): MediatorResult {
        return try {
            pageIndex = getPageIndex(loadType) ?: return MediatorResult.Success(
                endOfPaginationReached = true,
            )
            val limit = state.config.pageSize
            val offset = pageIndex * GIFS_PAGE_SIZE
            val response = withContext(Dispatchers.IO) {
                giphyApi.getSearched(
                    limit = limit,
                    offset = offset,
                    query = query,
                )
            }
            val gifs = response.data.map { it.toGifEntity() }
            giphyDao.save(gifs)
            MediatorResult.Success(
                endOfPaginationReached = gifs.size < limit,
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    companion object {
        const val GIFS_PAGE_SIZE = 25
    }
}
