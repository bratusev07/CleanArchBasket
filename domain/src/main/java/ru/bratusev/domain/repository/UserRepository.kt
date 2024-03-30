package ru.bratusev.domain.repository

import ru.bratusev.domain.models.AuthorizeResponse
import ru.bratusev.domain.models.RegistrationResponse
import ru.bratusev.domain.models.UserData

interface UserRepository {

    suspend fun authorizeUser(userData: UserData): AuthorizeResponse

    suspend fun registerUser(userData: UserData): RegistrationResponse
}