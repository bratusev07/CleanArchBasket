package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Team
import ru.bratusev.domain.repository.TeamsRepository


class CreateTeamUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(team: Team): Boolean {
        return teamsRepository.createTeam(team)
    }
}