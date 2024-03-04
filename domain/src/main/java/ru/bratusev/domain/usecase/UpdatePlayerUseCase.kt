package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository


class UpdatePlayerUseCase(private val teamRepository: TeamRepository) {

    fun execute(player: Player, index: Int): Boolean {
        return teamRepository.updatePlayer(player, index)
    }
}