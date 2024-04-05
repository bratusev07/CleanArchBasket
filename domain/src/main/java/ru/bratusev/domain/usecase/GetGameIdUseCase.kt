package ru.bratusev.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.TeamListResponse
import ru.bratusev.domain.repository.GameRepository
import ru.bratusev.domain.repository.TeamsRepository
import java.io.IOException

class GetGameIdUseCase(private val gameRepository: GameRepository) {

    operator fun invoke(gameModel: GameModel): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val data = gameRepository.getGameId(gameModel)
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}