package ua.glebm.testnatifetask.data.mapper

import ua.glebm.testnatifetask.data.database.entities.GifEntity
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

fun GifEntity.toGif(): Gif {
    return Gif(
        uniqueId = this.uniqueId,
        url = this.url,
        title = title,
    )
}
