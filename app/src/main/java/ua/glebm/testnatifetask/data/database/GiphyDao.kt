package ua.glebm.testnatifetask.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.glebm.testnatifetask.data.database.entities.GifEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@Dao
interface GiphyDao {

    @Query("SELECT * FROM gif_entity WHERE title LIKE :query || '%'")
    fun getPagingSource(
        query: String,
    ): PagingSource<Int, GifEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(launches: List<GifEntity>)
}
