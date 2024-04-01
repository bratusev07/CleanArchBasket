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

    override suspend fun getGames(): ArrayList<Game> {
        return getSupabaseService().postgrest["games"].select().decodeList<Game>() as ArrayList<Game>
    }

}