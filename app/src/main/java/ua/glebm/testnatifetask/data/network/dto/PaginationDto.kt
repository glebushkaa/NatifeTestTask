package ua.glebm.testnatifetask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

data class PaginationDto(
    @SerializedName("total_count") val totalCount: Int? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("offset") val offset: Int? = null,
)
