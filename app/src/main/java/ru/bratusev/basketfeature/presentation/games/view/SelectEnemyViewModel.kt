package ru.bratusev.basketfeature.presentation.games.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.domain.models.Player

class SelectEnemyViewModel() : ViewModel() {

    private val playersInGameMutable = MutableLiveData<ArrayList<Player>>()
    internal val playersInGame : LiveData<ArrayList<Player>> = playersInGameMutable

    private val playersMutable = MutableLiveData<ArrayList<Player>>()
    internal val players : LiveData<ArrayList<Player>> = playersMutable

    internal fun addToGame(player: Player){
        playersMutable.value!!.remove(player)
        playersInGameMutable.value!!.add(player)
        changeValue()
    }

    private fun changeValue() {
        playersMutable.value = playersMutable.value
        playersInGameMutable.value = playersInGameMutable.value
    }

    internal fun removeFromGame(player: Player){
        playersInGameMutable.value!!.remove(player)
        playersMutable.value!!.add(player)
        changeValue()
    }

    internal fun addPlayers(players: ArrayList<Player>){
        playersMutable.value = players
        playersInGameMutable.value = ArrayList()
    }
}