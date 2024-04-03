package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.bratusev.domain.models.GameModel

@Serializable
data class Game(
    val id: String = "",
    val date: String,
    @SerialName("team_a")
    val teamA: String,
    @SerialName("team_b")
    val teamB: String,
    @SerialName("user_id")
    var userId: String = "",
    @SerialName("game_name")
    var gameName: String
)

internal fun Game.toResponse(): GameModel{
    return GameModel(date, teamA, teamB, gameName)
}