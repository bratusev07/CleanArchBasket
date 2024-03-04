package ru.bratusev.domain.usecase

import ru.bratusev.domain.repository.TeamsRepository


class UpdateTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(name: String, index: Int): Boolean {
        return teamsRepository.updateTeam(name, index)
    }
}