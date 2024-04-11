package ru.bratusev.basketfeature.presentation.games.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.usecase.GetPlayersListUseCase

class SelectPlayersViewModel(
    private val getPlayersListUseCase: GetPlayersListUseCase
) : ViewModel() {

    private val playersMutable = MutableLiveData<ArrayList<Player>>()
    internal val players: LiveData<ArrayList<Player>> = playersMutable

    private val isLoadingMutable = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = isLoadingMutable

    private var teamId: String = ""

    internal fun setTeamId(id: String) {
        teamId = id
    }

    internal fun addToGame(player: Player) {
        playersMutable.value?.find { it.id == player.id }?.isInGame = true
        changeValue()
    }

    private fun changeValue() {
        playersMutable.value = playersMutable.value
    }

    internal fun removeFromGame(player: Player) {
        playersMutable.value?.find { it.id == player.id }?.isInGame = false
        changeValue()
    }

    internal fun getPlayers() {
        getPlayersListUseCase.invoke(teamId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    isLoadingMutable.value = false
                    playersMutable.value = result.data as ArrayList<Player>
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