package ru.bratusev.domain.usecase

import ru.bratusev.domain.repository.TeamsRepository

/**
 * Todo: Функционал удаления
 * */
class RemoveTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(index: Int): Boolean {
        return false
    }
}