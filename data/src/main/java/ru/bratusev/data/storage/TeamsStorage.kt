package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.TeamModel


interface TeamsStorage {

    fun getTeams() : ArrayList<TeamModel>

    fun removeTeam(index: Int) : Boolean

    fun updateTeam(name: String, index: Int) : Boolean
}