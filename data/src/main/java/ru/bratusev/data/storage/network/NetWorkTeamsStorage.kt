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

    override suspend fun getTeamById(id: String): TeamListDtoItem {
        return getSupabaseService().postgrest["teams"].select {
            eq("id", id)
        }.decodeAs<TeamListDtoItem>()
    }

    override suspend fun removeTeam(id: String) {
        getSupabaseService().postgrest["teams"].delete {
            eq("id",id)
        }
    }

    override suspend fun updateTeam(name: String, id: String) {
        getSupabaseService().postgrest["teams"].update(
            {
                set("name", name)
            }
        ){
            eq("id", id)
        }
    }

    override suspend fun createTeam(teamModel: TeamModel) {
        teamModel.userId = userId
        getSupabaseService().postgrest["teams"].insert(teamModel)
    }

}