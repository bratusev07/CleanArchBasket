package ru.bratusev.data.storage.network

import android.content.Context
import io.github.jan.supabase.postgrest.postgrest
import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.network.Common.getSupabaseService

class NetWorkTeamStorage(context: Context) : TeamStorage {
    override suspend fun getPlayers(teamId: String): ArrayList<PlayerModel> {
        return getSupabaseService().postgrest["players"].select {
            eq("team_id", teamId)
        }.decodeList<PlayerModel>() as ArrayList<PlayerModel>
    }

    override suspend fun removePlayer(id: String): Boolean {
        Common.retrofitService.removePlayer(id)
        return true
    }

    override suspend fun updatePlayer(player: PlayerModel, id: String): Boolean {
        Common.retrofitService.updatePlayer(id, player)
        return true
    }

    override suspend fun createPlayer(player: PlayerModel): Boolean {
        Common.retrofitService.createPlayer(player)
        return true
    }

}