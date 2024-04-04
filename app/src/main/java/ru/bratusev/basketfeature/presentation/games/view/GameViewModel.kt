package ru.bratusev.basketfeature.presentation.games.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.usecase.GetGameListUseCase
import ru.bratusev.domain.usecase.GetTeamByIdUseCase

class GameViewModel(
    private val getGameListUseCase: GetGameListUseCase,
    private val getTeamByIdUseCase: GetTeamByIdUseCase
) : ViewModel() {

    private val gameListMutable = MutableLiveData<ArrayList<GameModel>>()
    internal val gameList: LiveData<ArrayList<GameModel>> = gameListMutable

    internal fun getGames() {
        getGameListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    gameListMutable.value = result.data as ArrayList<GameModel>
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    internal fun getTeamById(id: String) {
        getTeamByIdUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {}
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}