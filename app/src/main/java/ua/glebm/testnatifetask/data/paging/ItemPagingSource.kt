package ua.glebm.testnatifetask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/18/2023
 */

class ItemPagingSource : PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val pageIndex = params.key ?: 1
        try {
        } catch (e: Exception) {
        }
        return LoadResult.Page(
            data = listOf(),
            prevKey = null,
            nextKey = null,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}
