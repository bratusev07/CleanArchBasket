package ru.bratusev.domain.repository

import ru.bratusev.domain.models.Player

interface TeamRepository {

    suspend fun getPlayers(teamId: String) : ArrayList<Player>
    suspend fun removePlayer(id: String) : Boolean
    suspend fun updatePlayer(player: Player) : Boolean
    suspend fun createPlayer(player: Player) : Boolean
}