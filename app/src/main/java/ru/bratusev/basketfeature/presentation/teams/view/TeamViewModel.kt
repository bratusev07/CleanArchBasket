package ru.bratusev.basketfeature.presentation.teams.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.usecase.CreatePlayerUseCase
import ru.bratusev.domain.usecase.GetPlayersListUseCase
import ru.bratusev.domain.usecase.RemovePlayerUseCase
import ru.bratusev.domain.usecase.UpdatePlayerUseCase

class TeamViewModel(
    private val createPlayerUseCase: CreatePlayerUseCase,
    private val removePlayerUseCase: RemovePlayerUseCase,
    private val updatePlayerUseCase: UpdatePlayerUseCase,
    private val getPlayersListUseCase: GetPlayersListUseCase

) : ViewModel() {

    private val namePattern = Regex("^.*(?=.{2,18})(?=.*[а-яА-я]).*\$")
    private val surnamePattern = Regex("^.*(?=.{2,18})(?=.*[а-яА-я]).*\$")
    private val lastNamePattern = Regex("^.*(?=.{0,18})(?=.*[а-яА-я]).*\$")

    private val playerListMutable = MutableLiveData<ArrayList<Player>>()
    internal val playerList: LiveData<ArrayList<Player>> = playerListMutable

    internal fun createPlayer(player: Player) {
        if (validateData(player)) createPlayerUseCase.execute(player)
    }

    internal fun removePlayer(index: Int) {
        removePlayerUseCase.execute(index)
    }

    internal fun updatePlayer(player: Player, index: Int) {
        if(validateData(player)) updatePlayerUseCase.execute(player, index)
    }

    internal fun getPlayersList() {
        playerListMutable.value = getPlayersListUseCase.execute()
    }

    private fun validateData(player: Player): Boolean {
        return player.name.matches(namePattern) &&
                player.lastName.matches(lastNamePattern) &&
                player.surname.matches(surnamePattern)
    }
}