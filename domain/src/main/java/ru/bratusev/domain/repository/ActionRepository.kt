package ru.bratusev.domain.repository

import ru.bratusev.domain.models.ActionModel

interface ActionRepository {

    suspend fun createAction(actionModel: ActionModel) : Boolean

    suspend fun getActions() : ArrayList<ActionModel>
}