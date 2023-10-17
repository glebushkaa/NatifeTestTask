package ua.glebm.testnatifetask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

data class GiphyResponse(
    @SerializedName("data") val data: List<GifDto> = emptyList(),
    @SerializedName("pagination") val pagination: PaginationDto? = null,
)
