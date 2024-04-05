package ru.bratusev.basketfeature.presentation.games.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.usecase.CreateGameUseCase
import ru.bratusev.domain.usecase.GetGameIdUseCase
import ru.bratusev.domain.usecase.GetPlayersListUseCase

class SelectEnemyViewModel(
    private val createGameUseCase: CreateGameUseCase,
    private val getPlayersListUseCase: GetPlayersListUseCase,
    private val getGameIdUseCase: GetGameIdUseCase
) : ViewModel() {

    private val playersMutable = MutableLiveData<ArrayList<Player>>()
    internal val players : LiveData<ArrayList<Player>> = playersMutable

    private var teamId: String = ""

    internal fun setTeamId(id: String){
        teamId = id
    }

    internal fun addToGame(player: Player){
        playersMutable.value?.find { it.id == player.id }?.isInGame = true
        changeValue()
    }

    private fun changeValue() {
        playersMutable.value = playersMutable.value
    }

    internal fun removeFromGame(player: Player){
        playersMutable.value?.find { it.id == player.id }?.isInGame = false
        changeValue()
    }

    internal fun getPlayers(){
        getPlayersListUseCase.invoke(teamId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    playersMutable.value = result.data as ArrayList<Player>
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun createGame(gameModel: GameModel) {
        createGameUseCase.invoke(gameModel).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getGameId(gameModel)
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getGameId(gameModel: GameModel) {
        getGameIdUseCase.invoke(gameModel).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    GameValues.gameId = result.data.toString()
                    GameValues.gameMoment.setGameId(GameValues.gameId)
                    Log.d("MyGameId", GameValues.gameId)
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}