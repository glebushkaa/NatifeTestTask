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

    @Query("SELECT * FROM gif_entity WHERE :id <= rowId AND title LIKE :query || '%'")
    fun getPagingSourceFromItem(
        query: String,
        id: Long,
    ): PagingSource<Int, GifEntity>

    @Query("SELECT rowId FROM gif_entity WHERE unique_id == :uniqueId")
    suspend fun getRowId(uniqueId: String): Long

    @Query(
        "SELECT * FROM gif_entity WHERE " +
            "abs(rowId - :itemId) <= :loadedItems/2  AND title LIKE :query || '%' " +
            "ORDER BY rowId LIMIT 10 OFFSET :loadedItems",
    )
    suspend fun getGifs(
        itemId: Long,
        query: String,
        loadedItems: Long,
    ): List<GifEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(launches: List<GifEntity>)
}
