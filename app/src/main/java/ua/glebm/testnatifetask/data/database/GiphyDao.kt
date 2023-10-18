package ua.glebm.testnatifetask.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ua.glebm.testnatifetask.data.database.entities.GifEntity
import ua.glebm.testnatifetask.data.database.entities.RemoveGifEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@Dao
interface GiphyDao {

    @Query("SELECT * FROM gif_entity WHERE title LIKE :query || '%' ORDER BY rowId ASC")
    fun getPagingSource(
        query: String,
    ): PagingSource<Int, GifEntity>

    @Query("SELECT * FROM gif_entity WHERE :id <= rowId AND title LIKE :query || '%' ORDER BY rowId ASC")
    fun getPagingSourceFromItem(
        query: String,
        id: Long,
    ): PagingSource<Int, GifEntity>

    @Query("SELECT rowId FROM gif_entity WHERE unique_id == :uniqueId")
    suspend fun getRowId(uniqueId: String): Long

    @Query("SELECT removed_id FROM remove_gif_entity")
    suspend fun getRemovedIds(): List<String>

    @Query("DELETE FROM gif_entity WHERE unique_id == :uniqueId")
    suspend fun removeGif(uniqueId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemovedId(removeGifEntity: RemoveGifEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: GifEntity)

    @Transaction
    suspend fun save(launches: List<GifEntity>) {
        val removedIds = getRemovedIds()
        launches.forEach {
            if (removedIds.contains(it.uniqueId)) return@forEach
            save(it)
        }
    }

    @Transaction
    suspend fun remove(uniqueId: String) {
        val removeGifEntity = RemoveGifEntity(uniqueId)
        removeGif(uniqueId)
        insertRemovedId(removeGifEntity)
    }
}
