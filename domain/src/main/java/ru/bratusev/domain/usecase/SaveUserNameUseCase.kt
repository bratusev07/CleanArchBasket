package ru.bratusev.domain.usecase

import ru.bratusev.domain.models.SaveUserNameParam
import ru.bratusev.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param : SaveUserNameParam): Boolean {
        val oldName = userRepository.getName()
        if(oldName.firstName == param.firstName && oldName.lastName == param.lastName) return true
        return userRepository.saveName(saveParam = param)
    }
}