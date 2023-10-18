package ua.glebm.testnatifetask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.glebm.testnatifetask.data.database.GiphyDatabase.Companion.GIPHY_DATABASE_VERSION
import ua.glebm.testnatifetask.data.database.entities.GifEntity
import ua.glebm.testnatifetask.data.database.entities.RemoveGifEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@Database(
    entities = [
        GifEntity::class,
        RemoveGifEntity::class,
    ],
    version = GIPHY_DATABASE_VERSION,
    exportSchema = true,
)
abstract class GiphyDatabase : RoomDatabase() {

    abstract fun giphyDao(): GiphyDao

    companion object {
        const val GIPHY_DATABASE_VERSION = 1
        const val GIPHY_DATABASE_NAME = "giphy_database"
    }
}
