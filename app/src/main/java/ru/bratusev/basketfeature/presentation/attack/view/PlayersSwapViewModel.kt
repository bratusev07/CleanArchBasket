package ru.bratusev.basketfeature.presentation.attack.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.domain.models.Player

class PlayersSwapViewModel : ViewModel() {

    private val playersMutable = MutableLiveData<ArrayList<Player>>()
    internal val players: LiveData<ArrayList<Player>> = playersMutable

    init {
        setPlayers()
    }

    private fun setPlayers() {
        playersMutable.value =
            if (GameValues.isEnemy) GameValues.enemyPlayers else GameValues.myPlayers
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


}