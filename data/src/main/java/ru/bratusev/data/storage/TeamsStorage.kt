package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.TeamListDto
import ru.bratusev.data.storage.models.TeamModel


interface TeamsStorage {

    suspend fun getTeams() : TeamListDto

    fun removeTeam(index: Int) : Boolean

    fun updateTeam(name: String, index: Int) : Boolean

    fun createTeam(teamModel: TeamModel): Boolean
}