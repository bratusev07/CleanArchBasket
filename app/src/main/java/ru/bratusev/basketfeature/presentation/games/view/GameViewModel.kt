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
import ru.bratusev.domain.models.TeamListResponse
import ru.bratusev.domain.usecase.GetGameListUseCase

class GameViewModel(private val getGameListUseCase: GetGameListUseCase) : ViewModel() {

    private val gameListMutable = MutableLiveData<ArrayList<GameModel>>()
    internal val gameList: LiveData<ArrayList<GameModel>> = gameListMutable

    internal fun getGames() {
        getGameListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    gameListMutable.value = result.data as ArrayList<GameModel>
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    Log.d("MyNewLog", "Resource.Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}