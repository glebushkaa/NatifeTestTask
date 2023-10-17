package ua.glebm.testnatifetask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

data class ImagesDto(
    @SerializedName("original") val original: FixedHeight? = null,
) {
    data class FixedHeight(
        @SerializedName("url") val url: String? = null,
    )
}
