package ru.bratusev.domain.models

data class HexagonPoint(
    var x: Float = 0f,
    var y: Float = 0f,
    var attackType: String,
    var shotResult: String,
    var value: Int = 0,
    var isEnemy: Boolean = false
)