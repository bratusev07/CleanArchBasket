package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.Game


interface GameStorage {

    suspend fun createGame(game: Game)

    suspend fun removeGame(id: String)

    suspend fun getGames() : ArrayList<Game>
}