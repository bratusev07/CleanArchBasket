package ru.bratusev.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.bratusev.domain.Resource
import ru.bratusev.domain.repository.TeamsRepository
import java.io.IOException


class UpdateTeamUseCase(private val teamsRepository: TeamsRepository) {

    operator fun invoke(name: String, id: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val data = teamsRepository.updateTeam(name, id)
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}