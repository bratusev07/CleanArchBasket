package ru.bratusev.domain.models

import java.io.Serializable

class Team(var name: String = "Без названия", val players: ArrayList<Player> = ArrayList()) : Serializable{
    override fun toString(): String {
        var playerString = "["
        for (player in players) {
            playerString += player.toString()
        }
        playerString += "]"
        return "Team $name $playerString"
    }
}