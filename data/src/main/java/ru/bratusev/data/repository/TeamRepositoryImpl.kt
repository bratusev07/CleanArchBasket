package ru.bratusev.data.repository

import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository

class TeamRepositoryImpl(private val teamStorage: TeamStorage) : TeamRepository {

    private fun parseModelToPlayers(list: ArrayList<PlayerModel>): ArrayList<Player> {
        val result = ArrayList<Player>()
        for (playerModel in list) {
            result.add(playerModel.toResponse())
        }
        return result
    }

    override suspend fun getPlayers(teamId: String): ArrayList<Player> {
        return parseModelToPlayers(teamStorage.getPlayers(teamId))
    }

    override suspend fun removePlayer(id: String): Boolean {
        teamStorage.removePlayer(id)
        return true
    }

    override suspend fun updatePlayer(player: Player): Boolean {
        teamStorage.updatePlayer(
            PlayerModel(
                id = player.id,
                name = player.name,
                number = player.number,
                fatherName = player.lastName,
                firstName = player.surname,
                userId = player.userId,
                teamId = player.teamId
            )
        )
        return true
    }

    override suspend fun createPlayer(player: Player): Boolean {
        teamStorage.createPlayer(
            PlayerModel(
                name = player.name,
                number = player.number,
                fatherName = player.lastName,
                firstName = player.surname,
                userId = player.userId,
                teamId = player.teamId
            )
        )
        return true
    }

}