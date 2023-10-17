package ua.glebm.testnatifetask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.glebm.testnatifetask.data.usecase.UseCaseLoggerImpl
import ua.glebm.testnatifetask.domain.usecase.core.UseCaseLogger
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {

    @Binds
    @Singleton
    fun provideUseCaseLogger(useCaseLoggerImpl: UseCaseLoggerImpl): UseCaseLogger
}
