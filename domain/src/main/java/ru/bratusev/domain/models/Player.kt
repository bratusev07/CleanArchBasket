package ru.bratusev.domain.models

import java.io.Serializable

class Player(
    val id: String = "",
    val userId: String = "",
    var teamId: String = "",
    var name: String = "Без имени",
    var number: Int = 1,
    var lastName: String = "",
    var surname: String = "",
    var isInGame: Boolean = false
): Serializable {
    override fun toString(): String {
        return " Player: $name $number "
    }
}