package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.repository.UserRepository

class RegistrationUseCase(private val userRepository: UserRepository) {

    fun execute(userData: UserData): Boolean {
        return userRepository.registerUser(userData)
    }
}