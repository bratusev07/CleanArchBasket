package ru.bratusev.domain.models

import java.io.Serializable

class GameMoment : Serializable{
    internal lateinit var timeZone: TimeZone
    internal lateinit var team: String
    internal lateinit var attackStartType: AttackStartType
    internal lateinit var timeType: TimeType
    internal lateinit var player: String
    internal var second: Int = 0
    internal lateinit var attackType: AttackType
    internal lateinit var resultType: ResultType
    internal var foul: Foul = Foul.NONE
    internal var shot: Shot = Shot.NONE
    internal var loss: Loss = Loss.NONE

    fun setTimeZone(timeZone: TimeZone): GameMoment {
        this.timeZone = timeZone
        return this
    }

    fun setTeam(team: String): GameMoment {
        this.team = team
        return this
    }

    fun setAttackStart(attackStartType: AttackStartType): GameMoment {
        this.attackStartType = attackStartType
        return this
    }

    fun setTimeType(timeType: TimeType): GameMoment {
        this.timeType = timeType
        return this
    }

    fun setPlayer(player: String): GameMoment {
        this.player = player
        return this
    }

    fun setSecond(second: Int): GameMoment {
        this.second = second
        return this
    }

    fun setAttackType(attackType: AttackType): GameMoment {
        this.attackType = attackType
        return this
    }

    fun setResultType(resultType: ResultType): GameMoment {
        this.resultType = resultType
        return this
    }

    fun setFoul(foul: Foul): GameMoment {
        this.foul = foul
        return this
    }

    fun setShot(shot: Shot): GameMoment {
        this.shot = shot
        return this
    }

    fun setLoss(loss: Loss): GameMoment {
        this.loss = loss
        return this
    }

    override fun toString(): String {
        return "$timeType $timeZone $player $loss"
    }
}

enum class TimeZone {
    TIME_1,
    TIME_2,
    TIME_3,
    TIME_4,
    TIME_OOT
}

enum class AttackStartType {
    SELECTION_IN_DEFENCE,
    INTERCEPTION,
    LIVE_BALL,
    DEAD_BALL,
    SELECTION_IN_ATTACK
}

enum class TimeType {
    TIME_14,
    TIME_24
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