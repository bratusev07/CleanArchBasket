package ru.bratusev.domain.models

class Team(var name: String = "Без названия", val players: ArrayList<Player> = ArrayList()){
    override fun toString(): String {
        var playerString = "["
        for (player in players) {
            playerString += player.toString()
        }
        playerString += "]"
        return "Team $name $playerString"
    }
}