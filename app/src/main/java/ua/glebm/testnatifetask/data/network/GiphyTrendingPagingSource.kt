package ua.glebm.testnatifetask.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.testnatifetask.data.mapper.toGif
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class GiphyTrendingPagingSource(
    private val giphyApi: GiphyApi,
) : PagingSource<Int, Gif>() {
    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return try {
            val page = params.key ?: 1
            val response = withContext(Dispatchers.IO) {
                giphyApi.getTrending(
                    limit = 20,
                    offset = (page - 1) * 20,
                )
            }
            val gifs = response.data.map { it.toGif() }

            LoadResult.Page(
                data = gifs,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (gifs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
