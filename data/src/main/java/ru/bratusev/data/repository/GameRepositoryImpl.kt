package ru.bratusev.data.repository

import ru.bratusev.data.storage.GameStorage
import ru.bratusev.data.storage.models.Game
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.repository.GameRepository

class GameRepositoryImpl(private val gameStorage: GameStorage) : GameRepository {

    override suspend fun createGame(gameModel: GameModel): Boolean {
        gameStorage.createGame(Game(date = gameModel.date, teamA = gameModel.myTeamId, teamB = gameModel.enemyTeamId))
        return true
    }

    override suspend fun getGames(): ArrayList<GameModel> {
        val result = ArrayList<GameModel>()
        for (game in gameStorage.getGames()) {
            result.add(game.toResponse())
        }
        return result
    }

}