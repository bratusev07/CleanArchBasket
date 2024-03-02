package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Team
import ru.bratusev.domain.repository.TeamsRepository

class GetTeamsListUseCase(private val teamsRepository: TeamsRepository) {

    fun execute(): ArrayList<Team> {
        return arrayListOf(Team("Химки"), Team("ЦСКА"),Team("Спартак"))
    }
}