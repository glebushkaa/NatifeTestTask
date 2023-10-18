package ua.glebm.testnatifetask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.glebm.testnatifetask.data.database.GiphyDatabase
import ua.glebm.testnatifetask.data.database.GiphyDatabase.Companion.GIPHY_DATABASE_NAME
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGiphyDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context = context,
        klass = GiphyDatabase::class.java,
        name = GIPHY_DATABASE_NAME,
    ).build()

    @Provides
    @Singleton
    fun provideGiphyDao(
        giphyDatabase: GiphyDatabase,
    ) = giphyDatabase.giphyDao()
}
