package ru.bratusev.domain.models

class Game (
    val gameName: String = "Empty",
    val gameMoments: ArrayList<GameMoment> = ArrayList()
)