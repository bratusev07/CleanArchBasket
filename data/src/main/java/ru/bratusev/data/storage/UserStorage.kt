package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.UserModel

interface UserStorage {

    fun authorize(userModel: UserModel): Boolean

    fun register(userModel: UserModel): Boolean
}