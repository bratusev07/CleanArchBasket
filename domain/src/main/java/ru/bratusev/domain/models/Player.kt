package ru.bratusev.domain.models

class Player(
    val id: String = "",
    val userId: String = "",
    var teamId: String = "",
    var name: String = "Без имени",
    var number: Int = 1,
    var lastName: String = "",
    var surname: String = "",
) {
    override fun toString(): String {
        return " Player: $name $number "
    }
}