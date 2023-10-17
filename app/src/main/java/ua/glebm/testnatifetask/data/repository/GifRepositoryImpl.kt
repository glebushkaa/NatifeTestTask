package ua.glebm.testnatifetask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.glebm.testnatifetask.data.network.GiphyApi
import ua.glebm.testnatifetask.data.network.GiphyTrendingPagingSource
import ua.glebm.testnatifetask.domain.repository.GifRepository
import ua.glebm.testnatifetask.model.Gif
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class GifRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi,
) : GifRepository {

    override fun getTrending(): Flow<PagingData<Gif>> {
        val config = PagingConfig(pageSize = 20)
        val pagingSourceFactory = { GiphyTrendingPagingSource(giphyApi) }
        val pager = Pager(
            config = config,
            pagingSourceFactory = pagingSourceFactory,
        )
        return pager.flow
    }
}
