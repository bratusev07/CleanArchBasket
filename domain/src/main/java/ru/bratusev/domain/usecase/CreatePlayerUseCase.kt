package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.Player
import ru.bratusev.domain.repository.TeamRepository


class CreatePlayerUseCase(private val teamRepository: TeamRepository) {

    fun execute(player: Player): Boolean {
        return teamRepository.createPlayer(player)
    }
}