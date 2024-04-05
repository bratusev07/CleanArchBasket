package ru.bratusev.data.storage.network

import android.content.Context
import io.github.jan.supabase.postgrest.postgrest
import ru.bratusev.data.storage.GameStorage
import ru.bratusev.data.storage.models.Game
import ru.bratusev.data.storage.network.Common.getSupabaseService
import ru.bratusev.data.storage.network.Common.userId

class NetWorkGameStorage(context: Context) : GameStorage {

    override suspend fun createGame(game: Game) {
        game.userId = userId
        getSupabaseService().postgrest["games"].insert(game)
    }

    override suspend fun removeGame(id: String) {
        getSupabaseService().postgrest["games"].delete { eq("id", id) }
    }

    override suspend fun getGames(): ArrayList<Game> {
        return getSupabaseService().postgrest["games"].select()
            .decodeList<Game>() as ArrayList<Game>
    }

    override suspend fun getGameId(game: Game): String {
        val result = getSupabaseService().postgrest["games"].select {
            eq("team_a", game.teamA)
            eq("team_b", game.teamB)
            eq("date", game.date)
            eq("game_name", game.gameName)
        }.decodeList<Game>()
        return result[result.lastIndex].id
    }

}