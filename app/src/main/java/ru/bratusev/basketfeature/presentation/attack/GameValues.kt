package ru.bratusev.basketfeature.presentation.attack

import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.TeamListResponse

object GameValues {

    internal lateinit var date: String
    internal lateinit var myTeam: TeamListResponse
    internal lateinit var enemyTeam: TeamListResponse
    internal lateinit var myPlayers: ArrayList<Player>
    internal lateinit var enemyPlayers: ArrayList<Player>
    internal var gameId: String = ""
    internal var gameMoment = GameMoment(gameId)
    internal var isEnemy: Boolean = false
}