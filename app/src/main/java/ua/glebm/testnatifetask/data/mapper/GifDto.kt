package ua.glebm.testnatifetask.data.mapper

import ua.glebm.testnatifetask.data.database.entities.GifEntity
import ua.glebm.testnatifetask.data.network.dto.GifDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

fun GifDto.toGifEntity(): GifEntity {
    return GifEntity(
        uniqueId = this.id ?: "",
        url = this.images?.original?.url ?: "",
        title = title ?: "",
    )
}
