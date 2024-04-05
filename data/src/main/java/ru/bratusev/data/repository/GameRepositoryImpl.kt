package ru.bratusev.data.repository

import ru.bratusev.data.storage.GameStorage
import ru.bratusev.data.storage.models.Game
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.repository.GameRepository

class GameRepositoryImpl(private val gameStorage: GameStorage) : GameRepository {

    override suspend fun createGame(gameModel: GameModel): Boolean {
        gameStorage.createGame(
            Game(
                id = gameModel.id,
                date = gameModel.date,
                teamA = gameModel.myTeamId,
                teamB = gameModel.enemyTeamId,
                gameName = gameModel.gameName
            )
        )
        return true
    }

    override suspend fun removeGame(id: String): Boolean {
        gameStorage.removeGame(id)
        return true
    }

    override suspend fun getGames(): ArrayList<GameModel> {
        val result = ArrayList<GameModel>()
        for (game in gameStorage.getGames()) {
            result.add(game.toResponse())
        }
        return result
    }

    override suspend fun getGameId(gameModel: GameModel): String {
        return gameStorage.getGameId(
            Game(
                id = gameModel.id,
                date = gameModel.date,
                teamA = gameModel.myTeamId,
                teamB = gameModel.enemyTeamId,
                gameName = gameModel.gameName
            )
        )
    }

}