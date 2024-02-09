package ru.bratusev.data.repository

import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.User
import ru.bratusev.data.storage.sharedPrefs.SharedPrefUserStorage
import ru.bratusev.domain.models.SaveUserNameParam
import ru.bratusev.domain.models.UserName
import ru.bratusev.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        return userStorage.save(User(firstName = saveParam.firstName, lastName = saveParam.lastName))
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}