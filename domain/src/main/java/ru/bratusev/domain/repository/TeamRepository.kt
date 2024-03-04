package ru.bratusev.domain.repository

import ru.bratusev.domain.models.Player

interface TeamRepository {

    fun getPlayers() : ArrayList<Player>

    fun removePlayer(index: Int) : Boolean

    fun updatePlayer(player: Player, index: Int) : Boolean

    fun createPlayer(player: Player) : Boolean
}