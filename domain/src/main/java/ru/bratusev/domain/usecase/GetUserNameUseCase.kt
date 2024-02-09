package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.UserName
import ru.bratusev.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }
}