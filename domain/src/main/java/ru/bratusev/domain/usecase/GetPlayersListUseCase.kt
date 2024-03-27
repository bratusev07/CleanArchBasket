package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository

class GetPlayersListUseCase(private val teamRepository: TeamRepository) {

    fun execute(): ArrayList<Player> {
        return teamRepository.getPlayers()
    }
}