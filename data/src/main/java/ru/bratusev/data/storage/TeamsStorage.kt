package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.TeamListDtoItem
import ru.bratusev.data.storage.models.TeamModel


interface TeamsStorage {

    suspend fun getTeams() : ArrayList<TeamListDtoItem>

    suspend fun removeTeam(id: String)

    suspend fun updateTeam(name: String, id: String)

    suspend fun createTeam(teamModel: TeamModel)
}