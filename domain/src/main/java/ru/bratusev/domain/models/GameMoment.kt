package ru.bratusev.domain.models

import java.io.Serializable

class GameMoment(id: String) : Serializable {
    var gameId: String = id
    var teamId: String = "NONE"
    var index: Int = 0
    var createdAt: String = "NONE"
    var quater: Int = 1
    var playersOnField = ArrayList<Player>()
    var typeOfPossession: String = AttackStartType.DEAD_BALL.toString()
    var passStory = ArrayList<Player>()
    var timeType: Int = 14
    var time: Int = 0
    var attackType: String = AttackType.EARLY_ATTACK.toString()
    var resultType: String = ResultType.LOSS.toString()
    var shot: String = Shot.NONE.toString()
    var zone: Int = 0
    var shotResult: String = ShotResult.MISS.toString()
    var assist = "NONE"
    var foulType: String = Foul.NONE.toString()
    var techFoulTeam: String = "NONE"
    var techFoulPlayer: String = "NONE"
    var techFoulRes = "NONE"
    var foulResult: Int = 0
    var loss: String = Loss.NONE.toString()


    fun setTimeZone(quater: Int): GameMoment {
        this.quater = quater
        return this
    }

    fun setGameId(id: String): GameMoment {
        this.gameId = id
        return this
    }

    fun addIndex(): GameMoment {
        index++
        return this
    }

    fun setPlayersOnField(players: ArrayList<Player>): GameMoment {
        playersOnField = players
        return this
    }

    fun setCreateTime(createdAt: String): GameMoment {
        this.createdAt = createdAt
        return this
    }

    fun addPassToStory(player: Player) {
        passStory.add(player)
        techFoulPlayer = player.id
    }

    fun setTeam(teamId: String): GameMoment {
        this.teamId = teamId
        techFoulTeam = teamId
        return this
    }

    fun setAttackStart(attackStartType: AttackStartType): GameMoment {
        this.typeOfPossession = attackStartType.toString()
        return this
    }

    fun setAttackStart(attackStartType: String): GameMoment {
        this.typeOfPossession = attackStartType
        return this
    }

    fun setTimeType(timeType: Int): GameMoment {
        this.timeType = timeType
        return this
    }

    fun setFoulResult(foulRes: Int): GameMoment {
        this.foulResult = foulRes
        return this
    }

    fun setZoneNumber(zoneNumber: Int): GameMoment {
        this.zone = zoneNumber
        return this
    }

    fun setIsHit(isHit: ShotResult): GameMoment {
        this.shotResult = isHit.toString()
        return this
    }

    fun setIsHit(isHit: String): GameMoment {
        this.shotResult = isHit
        return this
    }

    fun setSecond(second: Int): GameMoment {
        this.time = second
        return this
    }

    fun setAttackType(attackType: AttackType): GameMoment {
        this.attackType = attackType.toString()
        return this
    }

    fun setAttackType(attackType: String): GameMoment {
        this.attackType = attackType
        return this
    }

    fun setResultType(resultType: ResultType): GameMoment {
        this.resultType = resultType.toString()
        return this
    }

    fun setResultType(resultType: String): GameMoment {
        this.resultType = resultType
        return this
    }

    fun setFoul(foul: Foul): GameMoment {
        this.foulType = foul.toString()
        return this
    }

    fun setFoul(foul: String): GameMoment {
        this.foulType = foul
        return this
    }

    fun setShot(shot: Shot): GameMoment {
        this.shot = shot.toString()
        return this
    }

    fun setShot(shot: String): GameMoment {
        this.shot = shot
        return this
    }

    fun setLoss(loss: Loss): GameMoment {
        this.loss = loss.toString()
        return this
    }

    fun setLoss(loss: String): GameMoment {
        this.loss = loss
        return this
    }

    fun clearHistory() {
        passStory.clear()
    }

    fun setPassStory(passStory: ArrayList<Player>): GameMoment {
        this.passStory = passStory
        return this
    }

}

enum class AttackStartType {
    SELECTION_IN_DEFENCE,
    INTERCEPTION,
    LIVE_BALL,
    DEAD_BALL,
    SELECTION_IN_ATTACK
}

enum class ShotResult {
    MISS,
    HIT
}

enum class AttackType {
    QUICK_BREAKAWAY,
    EARLY_ATTACK,
    SECOND_CHANCE_ATTACK,
    POSITIONAL_ATTACK,
    BREAKING_PRESSURE,
    BREAKING_ZONE
}

enum class ResultType {
    FOUL,
    SHOT,
    LOSS
}

enum class Foul {
    SHOT_1,
    SHOT_2,
    SHOT_3,
    NOT_PUNCHY,
    TECHNICAL,
    NONE
}

enum class Shot {
    DRIVES,
    ISOLATION,
    TRANSITION,
    CATCH_SHOOT,
    PULL_UP,
    POST_UP,
    PNR_HANDLER,
    PNR_ROLLER,
    CUTS,
    OFF_SCREEN,
    HAND_OFF,
    NONE
}

enum class Loss {
    PASS_LOSS,
    TECHNICAL_LOSS,
    FOUL_IN_ATTACK,
    TACTICAL_LOSS,
    NONE
}