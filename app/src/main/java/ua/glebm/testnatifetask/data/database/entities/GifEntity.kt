package ua.glebm.testnatifetask.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@Entity(tableName = "gif_entity")
data class GifEntity(
    @PrimaryKey(false)
    @ColumnInfo("unique_id")
    val uniqueId: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("title") val title: String,
)
