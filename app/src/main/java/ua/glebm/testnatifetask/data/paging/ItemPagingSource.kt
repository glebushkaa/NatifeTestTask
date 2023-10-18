package ua.glebm.testnatifetask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.testnatifetask.data.database.GiphyDao
import ua.glebm.testnatifetask.data.database.entities.GifEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/18/2023
 */

class ItemPagingSource(
    private val id: Long,
    private val query: String,
    private val initialPage: Int,
    private val giphyDao: GiphyDao,
    private val pageSize: Int,
) : PagingSource<Int, GifEntity>() {

    private val lastIdsMap = mutableMapOf<Int, Long>()

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, GifEntity> = withContext(Dispatchers.IO) {
        val pageIndex = params.key ?: initialPage
        try {
            val list = if (pageIndex < initialPage) {
                fetchPreviousList(pageIndex)
            } else {
                fetchNextList(pageIndex)
            }
            return@withContext LoadResult.Page(
                data = list,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (list.size == pageSize) pageIndex + 1 else null,
            )
        } catch (e: Exception) {
            return@withContext LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    private suspend fun fetchPreviousList(pageIndex: Int): List<GifEntity> {
        val list = giphyDao.getListBeforeItem(
            query = query,
            id = lastIdsMap[pageIndex] ?: id,
            pageSize = pageSize,
        )
        if (pageIndex > 0) {
            val firstItemId = giphyDao.getRowId(list.first().uniqueId)
            lastIdsMap[pageIndex - 1] = giphyDao.getPreviousItemIdByQuery(
                query = query,
                id = firstItemId,
            )
        }
        return list
    }

    private suspend fun fetchNextList(pageIndex: Int): List<GifEntity> {
        val list = giphyDao.getListFromItem(
            query = query,
            id = lastIdsMap[pageIndex] ?: id,
            pageSize = pageSize,
        )
        val lastItemId = giphyDao.getRowId(list.last().uniqueId)
        lastIdsMap[pageIndex + 1] = giphyDao.getNextItemIdByQuery(
            query = query,
            id = lastItemId,
        )
        return list
    }
}
