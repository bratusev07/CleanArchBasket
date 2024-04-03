package ru.bratusev.data.storage.network

import android.content.Context
import io.github.jan.supabase.postgrest.postgrest
import ru.bratusev.data.storage.ActionStorage
import ru.bratusev.data.storage.GameStorage
import ru.bratusev.data.storage.models.ActionDto
import ru.bratusev.data.storage.models.Game
import ru.bratusev.data.storage.network.Common.getSupabaseService
import ru.bratusev.data.storage.network.Common.userId

class NetWorkActionStorage(context: Context) : ActionStorage {

    override suspend fun createAction(action: ActionDto) {
        getSupabaseService().postgrest["actions"].insert(action)
    }

    override suspend fun getActions(id: String): ArrayList<ActionDto> {
        return getSupabaseService().postgrest["actions"].select(){eq("game_id", id)}.decodeList<ActionDto>() as ArrayList<ActionDto>
    }

}