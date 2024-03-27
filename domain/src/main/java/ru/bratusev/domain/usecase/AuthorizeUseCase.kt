package ru.bratusev.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.AuthorizeResponse
import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.repository.UserRepository
import java.io.IOException

class AuthorizeUseCase(private val userRepository: UserRepository) {

    operator fun invoke(userData: UserData) : Flow<Resource<AuthorizeResponse>> = flow {
        try {
            emit(Resource.Loading())
            val data = userRepository.authorizeUser(userData)
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}