package ua.glebm.testnatifetask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.glebm.testnatifetask.data.network.GiphyApi
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val GIPHY_BASE_URL = "https://api.giphy.com/v1/gifs/"

    const val GIPHY_API_KEY = "WY77Y05rXCeIL6Z9wlGaQMt3IvjGD9WT"

    @Singleton
    @Provides
    fun provideGiphyRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GIPHY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }
}
