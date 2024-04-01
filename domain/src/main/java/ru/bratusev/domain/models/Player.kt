package ru.bratusev.domain.models

class Player(
    val id: String = "",
    val userId: String = "",
    val name: String = "Без имени",
    val number: Int = 1,
    val lastName: String = "",
    val surname: String = "",
) {
    override fun toString(): String {
        return " Player: $name $number "
    }
}