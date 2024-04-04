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
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.usecase.CreateGameUseCase
import ru.bratusev.domain.usecase.GetPlayersListUseCase

class SelectEnemyViewModel(
    private val createGameUseCase: CreateGameUseCase,
    private val getPlayersListUseCase: GetPlayersListUseCase
) : ViewModel() {

    private val playersInGameMutable = MutableLiveData<ArrayList<Player>>()
    internal val playersInGame : LiveData<ArrayList<Player>> = playersInGameMutable

    private val playersMutable = MutableLiveData<ArrayList<Player>>()
    internal val players : LiveData<ArrayList<Player>> = playersMutable

    private var teamId: String = ""

    internal fun setTeamId(id: String){
        teamId = id
    }

    internal fun addToGame(player: Player){
        playersMutable.value!!.remove(player)
        playersInGameMutable.value!!.add(player)
        player.isInGame = true
        changeValue()
    }

    private fun changeValue() {
        playersMutable.value = playersMutable.value
        playersInGameMutable.value = playersInGameMutable.value
    }

    internal fun removeFromGame(player: Player){
        playersInGameMutable.value!!.remove(player)
        playersMutable.value!!.add(player)
        player.isInGame = false
        changeValue()
    }

    internal fun getPlayers(){
        getPlayersListUseCase.invoke(teamId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    playersMutable.value = result.data as ArrayList<Player>
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

    fun createGame(gameModel: GameModel) {
        createGameUseCase.invoke(gameModel).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
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