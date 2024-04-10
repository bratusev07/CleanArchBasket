package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.Player

@Serializable
data class ActionDto(
    val id: String = "",
    @SerialName("game_id")
    val gameId: String,
    @SerialName("team_id")
    val teamId: String,
    val index: Int,
    @SerialName("created_at")
    val createdAt: String,
    val quater: Int,
    val playersOnField: ArrayList<PlayerModel>,
    val typeOfPossession: String,
    val possessions: ArrayList<PlayerModel>,
    val timeType: Int,
    val time: Int,
    val attackType: String,
    val result: String,
    val playType: String,
    val zone: Int,
    val shotResult: String,
    val assist: String,
    val foulType: String,
    val techFoulTeam: String,
    val techFoulPlayer: String,
    val techFoulRes: String,
    val foulResult: Int,
    val lossType: String,
    )

internal fun ActionDto.toResponse(): GameMoment {
    return GameMoment(gameId)
        .setTeam(teamId)
        .setCreateTime(createdAt)
        .setTimeZone(quater)
        .setPlayersOnField(parseModelToPlayers(playersOnField))
        .setAttackStart(typeOfPossession)
        .setPassStory(parseModelToPlayers(possessions))
        .setTimeType(timeType)
        .setSecond(time)
        .setAttackType(attackType)
        .setResultType(result)
        .setShot(playType)
        .setZoneNumber(zone)
        .setIsHit(shotResult)
        .setFoul(foulType)
}

private fun parseModelToPlayers(list: ArrayList<PlayerModel>): ArrayList<Player> {
    val result = ArrayList<Player>()
    for (playerModel in list) {
        result.add(playerModel.toResponse())
    }
    return result
}
