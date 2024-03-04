package ru.bratusev.data.repository

import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.repository.TeamsRepository

class TeamsRepositoryImpl(private val teamsStorage: TeamsStorage) : TeamsRepository {

    override fun getTeams(): ArrayList<Team> {
        return parseModelToTeams(teamsStorage.getTeams())
    }

    override fun removeTeam(index: Int): Boolean {
        return teamsStorage.removeTeam(index = index)
    }

    override fun updateTeam(name: String, index: Int): Boolean {
        return teamsStorage.updateTeam(name = name, index = index)
    }

    override fun createTeam(team: Team): Boolean {
        return teamsStorage.createTeam(TeamModel(team.name, parsePlayersToModel(team.players)))
    }


    private fun parseModelToTeams(list: ArrayList<TeamModel>) : ArrayList<Team>{
        val result = ArrayList<Team>()
        for (teamModel in list) {
            result.add(Team(teamModel.name, parseModelToPlayers(teamModel.players)))
        }
        return result
    }

    private fun parseModelToPlayers(list: ArrayList<PlayerModel>) : ArrayList<Player>{
        val result = ArrayList<Player>()
        for (playerModel in list) {
            result.add(Player(playerModel.name, playerModel.number))
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