package ru.bratusev.domain.usecase

import ru.bratusev.domain.repository.TeamsRepository


class RemoveTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(index: Int): Boolean {
        return teamsRepository.removeTeam(index)
    }
}