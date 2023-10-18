package ua.glebm.testnatifetask.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/18/2023
 */

@Entity("remove_gif_entity")
data class RemoveGifEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("removed_id")
    val removedId: String,
)
