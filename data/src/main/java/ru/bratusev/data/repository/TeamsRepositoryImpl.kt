package ru.bratusev.data.repository

import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.TeamListDtoItem
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.models.TeamListResponse
import ru.bratusev.domain.repository.TeamsRepository

class TeamsRepositoryImpl(private val teamsStorage: TeamsStorage) : TeamsRepository {

    override suspend fun getTeams(): ArrayList<TeamListResponse> {
        return parseModelToTeams(teamsStorage.getTeams())
    }

    override suspend fun getTeamById(id: String): TeamListResponse {
        return teamsStorage.getTeamById(id).toResponse()
    }

    override suspend fun removeTeam(id: String): Boolean {
        teamsStorage.removeTeam(id = id)
        return true
    }

    override suspend fun updateTeam(name: String, id: String): Boolean {
        teamsStorage.updateTeam(name = name, id = id)
        return true
    }

    override suspend fun createTeam(team: Team): Boolean {
        teamsStorage.createTeam(TeamModel(team.name))
        return true
    }


    private fun parseModelToTeams(list: ArrayList<TeamListDtoItem>) : ArrayList<TeamListResponse>{
        val result = ArrayList<TeamListResponse>()
        for (teamModel in list) {
            result.add(teamModel.toResponse())
        }
        return result
    }

    private fun parsePlayersToModel(list: ArrayList<Player>) : ArrayList<PlayerModel>{
        val result = ArrayList<PlayerModel>()
        for (playerModel in list) {
            result.add(PlayerModel(playerModel.name, playerModel.number))
        }
        return result
    }
}