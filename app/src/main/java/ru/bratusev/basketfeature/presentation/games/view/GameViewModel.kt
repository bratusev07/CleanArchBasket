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
import ru.bratusev.domain.usecase.RemoveGameUseCase

class GameViewModel(
    private val getGameListUseCase: GetGameListUseCase,
    private val getTeamByIdUseCase: GetTeamByIdUseCase,
    private val removeGameUseCase: RemoveGameUseCase
) : ViewModel() {

    private val gameListMutable = MutableLiveData<ArrayList<GameModel>>()
    internal val gameList: LiveData<ArrayList<GameModel>> = gameListMutable

    private val isLoadingMutable = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = isLoadingMutable

    internal fun getGames() {
        getGameListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    isLoadingMutable.value = false
                    gameListMutable.value = result.data as ArrayList<GameModel>
                }
                is Resource.Error -> {
                    isLoadingMutable.value = false
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    isLoadingMutable.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    internal fun removeGame(id: String) {
        removeGameUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    isLoadingMutable.value = false
                    getGames()
                }
                is Resource.Error -> {
                    isLoadingMutable.value = false
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    isLoadingMutable.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    internal fun getTeamById(id: String) {
        getTeamByIdUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    isLoadingMutable.value = false
                }
                is Resource.Error -> {
                    isLoadingMutable.value = false
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    isLoadingMutable.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}