package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.repository.TeamRepository
import ru.bratusev.domain.repository.TeamsRepository

class GetPlayersListUseCase(private val teamRepository: TeamRepository) {

    fun execute(): ArrayList<Player> {
        return teamRepository.getPlayers()
    }
}