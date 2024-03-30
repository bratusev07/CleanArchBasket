package ru.bratusev.domain.repository

import ru.bratusev.domain.models.Team
import ru.bratusev.domain.models.TeamListResponse

interface TeamsRepository {

    suspend fun getTeams() : ArrayList<TeamListResponse>

    fun removeTeam(index: Int) : Boolean

    fun updateTeam(name: String, index: Int) : Boolean

    fun createTeam(team: Team): Boolean
}