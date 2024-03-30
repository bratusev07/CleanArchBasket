package ru.bratusev.data.storage.models

import com.google.gson.annotations.SerializedName
import ru.bratusev.domain.models.TeamListResponse

data class TeamListDtoItem(
    val id: String,
    val name: String,
    @SerializedName("user_id")
    val userId: String
)

fun TeamListDtoItem.toResponse() : TeamListResponse {
    return TeamListResponse(id, name, userId)
}