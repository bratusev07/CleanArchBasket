package ru.bratusev.data.storage.network

import android.content.Context
import io.github.jan.supabase.postgrest.postgrest
import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.network.Common.getSupabaseService
import ru.bratusev.data.storage.network.Common.userId

class NetWorkTeamStorage(context: Context) : TeamStorage {
    override suspend fun getPlayers(teamId: String): ArrayList<PlayerModel> {
        return getSupabaseService().postgrest["players"].select {
            eq("team_id", teamId)
        }.decodeList<PlayerModel>() as ArrayList<PlayerModel>
    }

    override suspend fun removePlayer(id: String) {
        getSupabaseService().postgrest["players"].delete {
            eq("id", id)
        }
    }

    override suspend fun updatePlayer(player: PlayerModel) {
        player.userId = userId
        getSupabaseService().postgrest["players"].update({
            set("name", player.name)
            set("father_name", player.fatherName)
            set("first_name", player.firstName)
            set("number", player.number)
            set("user_id", player.userId)
            set("team_id", player.teamId)
        }){
            eq("id", player.id)
        }
    }

    override suspend fun createPlayer(player: PlayerModel) {
        player.userId = userId
        getSupabaseService().postgrest["players"].insert(player)
    }

}