package ru.bratusev.domain.repository

import ru.bratusev.domain.models.GameMoment

interface ActionRepository {

    suspend fun createAction(gameMoment: GameMoment) : Boolean

    suspend fun getActions(id: String) : ArrayList<GameMoment>
}