package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Team
import ru.bratusev.domain.repository.TeamsRepository

/**
 * Todo: Функционал добавления команды
 * */
class CreateTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(team: Team): Boolean {
        return false
    }
}