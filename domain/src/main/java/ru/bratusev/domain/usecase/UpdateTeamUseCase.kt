package ru.bratusev.domain.usecase

import ru.bratusev.domain.repository.TeamsRepository

/**
 * Todo: Функционал обновления названия
 * */
class UpdateTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(name: String, index: Int): Boolean {
        return false
    }
}