package ru.bratusev.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.repository.ActionRepository
import java.io.IOException

class GetActionsUseCase(private val actionRepository: ActionRepository) {

    operator fun invoke(id: String) : Flow<Resource<ArrayList<GameMoment>>> = flow{
        try {
            emit(Resource.Loading())
            val data = actionRepository.getActions(id)
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}