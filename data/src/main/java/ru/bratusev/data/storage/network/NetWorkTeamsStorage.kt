package ru.bratusev.data.storage.network

import android.content.Context
import io.github.jan.supabase.postgrest.postgrest
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.TeamListDtoItem
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.network.Common.getSupabaseService
import ru.bratusev.data.storage.network.Common.userId

class NetWorkTeamsStorage(context: Context) : TeamsStorage {

    override suspend fun getTeams(): ArrayList<TeamListDtoItem> {
        val response = getSupabaseService().postgrest["teams"].select {
            eq("user_id", userId)
        }
        return response.decodeList<TeamListDtoItem>() as ArrayList<TeamListDtoItem>
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