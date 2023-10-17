package ua.glebm.testnatifetask.data.mapper

import ua.glebm.testnatifetask.data.network.dto.GifDto
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

fun GifDto.toGif(): Gif {
    return Gif(
        id = this.id ?: "",
        url = this.images?.original?.url ?: "",
        title = title ?: "",
    )
}
