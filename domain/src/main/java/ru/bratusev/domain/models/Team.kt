package ru.bratusev.domain.models

import java.io.Serializable

class Team(val teamId: String = "", var name: String = "Без названия") : Serializable{
    override fun toString(): String {
        val playerString = "["
        return "Team $name $playerString"
    }
}