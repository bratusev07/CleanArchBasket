package ru.bratusev.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.repository.GameRepository
import java.io.IOException

class CreateGameUseCase(private val gameRepository: GameRepository) {

    operator fun invoke(gameModel: GameModel) : Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading())
            val data = gameRepository.createGame(gameModel)
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}