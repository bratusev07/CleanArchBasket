package ru.bratusev.domain.repository

import ru.bratusev.domain.models.Team
import ru.bratusev.domain.models.TeamListResponse

interface TeamsRepository {

    suspend fun getTeams() : ArrayList<TeamListResponse>

    suspend fun getTeamById(id: String) : TeamListResponse

    suspend fun removeTeam(id: String) : Boolean

    suspend fun updateTeam(name: String, id: String) : Boolean

    suspend fun createTeam(team: Team): Boolean
}