package ru.bratusev.domain.models

data class VerticalPoint(
    val key: Int,
    var value: Int,
    val isEnemy: Boolean = false
)