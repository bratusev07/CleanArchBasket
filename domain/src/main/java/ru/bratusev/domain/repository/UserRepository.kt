package ru.bratusev.domain.repository

import ru.bratusev.domain.models.UserData

interface UserRepository {

    fun authorizeUser(userData: UserData): Boolean

    fun registerUser(userData: UserData): Boolean
}