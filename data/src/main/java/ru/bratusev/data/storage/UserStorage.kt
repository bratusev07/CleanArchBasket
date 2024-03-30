package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.AuthorizeDto
import ru.bratusev.data.storage.models.RegistrationDto
import ru.bratusev.data.storage.models.UserModel

interface UserStorage {

    suspend fun authorize(userModel: UserModel): AuthorizeDto

    suspend fun register(userModel: UserModel): RegistrationDto
}