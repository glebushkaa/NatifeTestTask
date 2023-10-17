package ua.glebm.testnatifetask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.glebm.testnatifetask.data.repository.GifRepositoryImpl
import ua.glebm.testnatifetask.domain.repository.GifRepository
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindGifRepository(impl: GifRepositoryImpl): GifRepository
}
