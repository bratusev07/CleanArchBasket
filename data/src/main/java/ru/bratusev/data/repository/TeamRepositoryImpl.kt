package ru.bratusev.data.repository

import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository

class TeamRepositoryImpl(private val teamStorage: TeamStorage) : TeamRepository {

    private fun parseModelToPlayers(list: ArrayList<PlayerModel>): ArrayList<Player> {
        val result = ArrayList<Player>()
        for (playerModel in list) {
            result.add(Player(playerModel.id, playerModel.userId, playerModel.name, playerModel.number, playerModel.fatherName, playerModel.firstName))
        }
        return result
    }

    override suspend fun getPlayers(teamId: String): ArrayList<Player> {
        return parseModelToPlayers(teamStorage.getPlayers(teamId))
    }

    override suspend fun removePlayer(id: String): Boolean {
        return teamStorage.removePlayer(id)
    }

    override suspend fun updatePlayer(player: Player, id: String): Boolean {
        return teamStorage.updatePlayer(
            PlayerModel(
                player.name,
                player.number,
                player.lastName,
                player.surname
            ), id
        )
    }

    override suspend fun createPlayer(player: Player): Boolean {
        return teamStorage.createPlayer(
            PlayerModel(
                player.name,
                player.number,
                player.lastName,
                player.surname
            )
        )
    }

}