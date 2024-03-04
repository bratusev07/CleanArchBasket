package ru.bratusev.domain.usecase

import ru.bratusev.domain.repository.TeamRepository


class RemovePlayerUseCase(private val teamRepository: TeamRepository) {

    fun execute(index: Int): Boolean {
        return teamRepository.removePlayer(index)
    }
}