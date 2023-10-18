@file:OptIn(ExperimentalPagingApi::class)

package ua.glebm.testnatifetask.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.glebm.testnatifetask.data.database.GiphyDao
import ua.glebm.testnatifetask.data.mapper.toGif
import ua.glebm.testnatifetask.data.network.GiphyApi
import ua.glebm.testnatifetask.data.paging.GiphyPagingMediator
import ua.glebm.testnatifetask.data.paging.GiphyPagingMediator.Companion.GIFS_PAGE_SIZE
import ua.glebm.testnatifetask.data.paging.ItemPagingSource
import ua.glebm.testnatifetask.domain.repository.GifRepository
import ua.glebm.testnatifetask.model.Gif
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class GifRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi,
    private val giphyDao: GiphyDao,
) : GifRepository {

    override suspend fun removeGif(uniqueId: String) {
        giphyDao.remove(uniqueId)
    }

    override fun getSearchingGifs(
        query: String,
    ): Flow<PagingData<Gif>> {
        val config = PagingConfig(pageSize = GIFS_PAGE_SIZE)
        val mediator = GiphyPagingMediator(
            giphyApi = giphyApi,
            giphyDao = giphyDao,
            query = query,
            pageSize = GIFS_PAGE_SIZE,
        )
        val pager = Pager(
            config = config,
            remoteMediator = mediator,
            pagingSourceFactory = { giphyDao.getPagingSource(query) },
        )
        return pager.flow.map {
            it.map { entity -> entity.toGif() }
        }
    }

    override suspend fun getPagerGifs(
        query: String,
        uniqueId: String,
        position: Int,
    ): Flow<PagingData<Gif>> {
        val itemId = giphyDao.getRowId(uniqueId)
        val config = PagingConfig(pageSize = FULLSCREEN_PAGE_SIZE)
        val mediator = GiphyPagingMediator(
            giphyApi = giphyApi,
            giphyDao = giphyDao,
            query = query,
            pageSize = FULLSCREEN_PAGE_SIZE,
        )
        val initialPage = (position / FULLSCREEN_PAGE_SIZE).coerceAtLeast(FIRST_PAGE)
        val pagingSource = ItemPagingSource(
            id = itemId,
            query = query,
            initialPage = initialPage,
            giphyDao = giphyDao,
            pageSize = FULLSCREEN_PAGE_SIZE,
        )
        val pager = Pager(
            config = config,
            remoteMediator = mediator,
            pagingSourceFactory = { pagingSource },
        )
        return pager.flow.map {
            it.map { entity -> entity.toGif() }
        }
    }

    private companion object {
        const val FULLSCREEN_PAGE_SIZE = 10
        const val FIRST_PAGE = 1
    }
}
