package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.PlayerModel


interface TeamStorage {

    suspend fun getPlayers(teamId: String) : ArrayList<PlayerModel>

    suspend fun removePlayer(id: String)

    suspend fun updatePlayer(player: PlayerModel)

    suspend fun createPlayer(player: PlayerModel)
}