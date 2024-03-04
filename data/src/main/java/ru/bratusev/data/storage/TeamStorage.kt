package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.PlayerModel


interface TeamStorage {

    fun getPlayers() : ArrayList<PlayerModel>

    fun removePlayer(index: Int) : Boolean

    fun updatePlayer(player: PlayerModel, index: Int) : Boolean

    fun createPlayer(player: PlayerModel) : Boolean
}