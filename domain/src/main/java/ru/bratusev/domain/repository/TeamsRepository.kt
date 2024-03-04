package ru.bratusev.domain.repository

import ru.bratusev.domain.models.Team

interface TeamsRepository {

    fun getTeams() : ArrayList<Team>

    fun removeTeam(index: Int) : Boolean

    fun updateTeam(name: String, index: Int) : Boolean

    fun createTeam(team: Team): Boolean
}