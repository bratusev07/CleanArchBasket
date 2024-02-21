package ru.bratusev.data.repository

import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.UserModel
import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun authorizeUser(userData: UserData): Boolean {
        return userStorage.authorize(userModel = UserModel(mail = userData.mail, password = userData.password))
    }

    override fun registerUser(userData: UserData): Boolean {
        return userStorage.register(userModel = UserModel(mail = userData.mail, password = userData.password))
    }
}