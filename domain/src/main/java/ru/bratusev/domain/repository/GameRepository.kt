package ru.bratusev.domain.repository

import ru.bratusev.domain.models.GameModel

interface GameRepository {

    suspend fun createGame(gameModel: GameModel) : Boolean

    suspend fun removeGame(id: String) : Boolean

    suspend fun getGames() : ArrayList<GameModel>
}