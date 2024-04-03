package ru.bratusev.data.repository

import ru.bratusev.data.storage.ActionStorage
import ru.bratusev.data.storage.models.ActionDto
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.ActionModel
import ru.bratusev.domain.repository.ActionRepository

class ActionRepositoryImpl(private val actionStorage: ActionStorage) : ActionRepository{

    override suspend fun createAction(actionModel: ActionModel): Boolean {
        //actionStorage.createAction(ActionDto(actionModel.id))
        return true
    }

    override suspend fun getActions(): ArrayList<ActionModel> {
        val result = ArrayList<ActionModel>()
        for (action in actionStorage.getActions()) {
            result.add(action.toResponse())
        }
        return result
    }
}