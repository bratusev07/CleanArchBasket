package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.bratusev.domain.models.Player

@Serializable
class PlayerModel(
    val name: String = "Без имени",
    val number: Int = 1,
    @SerialName("first_name")
    val firstName: String? = "",
    @SerialName("father_name")
    val fatherName: String? = "",
    @SerialName("team_id")
    val teamId: String = "",
    val id: String = "",
    @SerialName("user_id")
    var userId: String = "",
)

internal fun PlayerModel.toResponse(): Player {
    return Player(
        id = id,
        userId = userId,
        teamId = teamId,
        name = name,
        surname = firstName?: "Без имени",
        lastName = fatherName?: "Без имени",
        number = number
    )
}