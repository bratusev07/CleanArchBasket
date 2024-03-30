package ru.bratusev.data.storage.models

import ru.bratusev.domain.models.TeamListResponse

@kotlinx.serialization.Serializable
data class TeamListDtoItem(
    val id: String,
    val name: String,
    val user_id: String
)

fun TeamListDtoItem.toResponse() : TeamListResponse {
    return TeamListResponse(id, name, user_id)
}