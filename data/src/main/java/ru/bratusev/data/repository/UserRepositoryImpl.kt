package ru.bratusev.data.repository

import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.UserModel
import ru.bratusev.data.storage.models.toResponse
import ru.bratusev.domain.models.AuthorizeResponse
import ru.bratusev.domain.models.RegistrationResponse
import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override suspend fun authorizeUser(userData: UserData): AuthorizeResponse {
        return userStorage.authorize(userModel = UserModel(mail = userData.mail, password = userData.password)).toResponse()
    }

    override suspend fun registerUser(userData: UserData): RegistrationResponse {
        return userStorage.register(userModel = UserModel(mail = userData.mail, password = userData.password)).toResponse()
    }
}