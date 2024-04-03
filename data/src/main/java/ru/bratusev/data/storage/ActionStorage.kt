package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.ActionDto

interface ActionStorage {

    suspend fun createAction(action: ActionDto)

    suspend fun getActions() : ArrayList<ActionDto>
}