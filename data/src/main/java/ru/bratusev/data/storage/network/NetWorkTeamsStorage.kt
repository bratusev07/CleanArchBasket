package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.TeamListDto
import ru.bratusev.data.storage.models.TeamListDtoItem
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.network.Common.key
import ru.bratusev.data.storage.network.Common.token
import ru.bratusev.data.storage.network.Common.userId

class NetWorkTeamsStorage(context: Context) : TeamsStorage {

    override suspend fun getTeams(): TeamListDto {
        return Common.retrofitService.getTeams(key, "Bearer $token", "eq.${userId}")
    }

    override fun removeTeam(index: Int): Boolean {
        Common.retrofitService.removeTeam(index)
        return true
    }

    override fun updateTeam(name: String, index: Int): Boolean {
        Common.retrofitService.updateTeam(index, name)
        return true
    }

    override fun createTeam(teamModel: TeamModel): Boolean {
        Common.retrofitService.createTeam(teamModel)
        return true
    }

}