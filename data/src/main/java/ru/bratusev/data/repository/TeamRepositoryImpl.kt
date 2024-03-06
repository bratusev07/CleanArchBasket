package ru.bratusev.data.repository

import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository

class TeamRepositoryImpl(private val teamStorage: TeamStorage) : TeamRepository {

    private fun parseModelToPlayers(list: ArrayList<PlayerModel>): ArrayList<Player> {
        val result = ArrayList<Player>()
        for (playerModel in list) {
            result.add(Player(playerModel.name, playerModel.number, playerModel.lastName, playerModel.surname))
        }
        return result
    }

    override fun getPlayers(): ArrayList<Player> {
        return parseModelToPlayers(teamStorage.getPlayers())
    }

    override fun removePlayer(index: Int): Boolean {
        return teamStorage.removePlayer(index)
    }

    override fun updatePlayer(player: Player, index: Int): Boolean {
        return teamStorage.updatePlayer(
            PlayerModel(
                player.name,
                player.number,
                player.lastName,
                player.surname
            ), index
        )
    }

    override fun createPlayer(player: Player): Boolean {
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