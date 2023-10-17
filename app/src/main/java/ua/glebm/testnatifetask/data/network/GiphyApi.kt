package ua.glebm.testnatifetask.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ua.glebm.testnatifetask.data.network.dto.GiphyResponse
import ua.glebm.testnatifetask.di.NetworkModule.GIPHY_API_KEY

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

interface GiphyApi {

    @GET("trending")
    suspend fun getTrending(
        @Query("api_key") apiKey: String = GIPHY_API_KEY,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): GiphyResponse

    @GET("search")
    suspend fun getSearched(
        @Query("api_key") apiKey: String = GIPHY_API_KEY,
        @Query("q") query: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    )
}
