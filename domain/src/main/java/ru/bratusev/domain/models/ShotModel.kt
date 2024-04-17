package ru.bratusev.domain.models

data class ShotModel(
    val zone: Int,
    val result: String,
    val isEnemy : Boolean = false
)
