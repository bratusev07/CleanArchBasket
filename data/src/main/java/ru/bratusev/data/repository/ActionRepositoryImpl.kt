package ru.bratusev.data.repository

import ru.bratusev.data.storage.ActionStorage
import ru.bratusev.data.storage.models.ActionDto
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.ActionRepository

class ActionRepositoryImpl(private val actionStorage: ActionStorage) : ActionRepository {

    override suspend fun createAction(gameMoment: GameMoment): Boolean {
        actionStorage.createAction(
            ActionDto(
                gameId = gameMoment.gameId,
                teamId = gameMoment.teamId,
                index = gameMoment.index,
                //createdAt = gameMoment.createdAt,
                quater = gameMoment.quater,
                playersOnField = parsePlayerToModel(gameMoment.playersOnField),
                typeOfPossession = gameMoment.typeOfPossession,
                possessions = parsePlayerToModel(gameMoment.passStory),
                timeType = gameMoment.timeType,
                time = gameMoment.time,
                attackType = gameMoment.attackType,
                result = gameMoment.resultType,
                playType = gameMoment.shot,
                zone = gameMoment.zone,
                shotResult = gameMoment.shotResult,
                assist = gameMoment.assist,
                foulType = gameMoment.foulType,
                techFoulTeam = gameMoment.techFoulTeam,
                techFoulPlayer = gameMoment.techFoulPlayer,
                techFoulRes = gameMoment.techFoulRes,
                foulResult = gameMoment.foulResult,
                lossType = gameMoment.loss
            )
        )
        return true
    }

    private fun parsePlayerToModel(players: ArrayList<Player>): ArrayList<PlayerModel> {
        val result = ArrayList<PlayerModel>()
        for (player in players) {
            result.add(
                PlayerModel(
                    player.name, player.number, player.surname, player.lastName, player.teamId,
                    player.id, player.userId
                )
            )
        }
        return result
    }

    override suspend fun getActions(id: String): ArrayList<GameMoment> {
        val result = ArrayList<GameMoment>()
        for (action in actionStorage.getActions(id)) {
            result.add(action.toResponse())
        }
        return result
    }
}