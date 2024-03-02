package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.TeamModel

class NetWorkTeamStorage(context: Context) : TeamsStorage {

    override fun getTeams(): ArrayList<TeamModel> {
        Common.retrofitService.getTeams()
        return arrayListOf(TeamModel("Химки"), TeamModel("ЦСКА"), TeamModel("Спартак"))
    }

    override fun removeTeam(index: Int): Boolean {
        Common.retrofitService.removeTeam(index)
        return true
    }

    override fun updateTeam(name: String, index: Int): Boolean {
        Common.retrofitService.updateTeam(index, name)
        return true
    }

}