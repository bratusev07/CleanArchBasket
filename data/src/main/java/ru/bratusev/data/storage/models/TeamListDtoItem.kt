package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.bratusev.domain.models.TeamListResponse

@Serializable
data class TeamListDtoItem(
    val id: String,
    val name: String,
    @SerialName("user_id")
    val userId: String
)

fun TeamListDtoItem.toResponse() : TeamListResponse {
    return TeamListResponse(id, name, userId)
}