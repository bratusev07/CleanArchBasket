package ru.bratusev.basketfeature.presentation.teams.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
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

    private var teamId = ""

    internal fun createPlayer(player: Player) {
        player.teamId = teamId
        if (validateData(player)) createPlayerUseCase.invoke(player).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    getPlayersList(teamId)
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

    internal fun removePlayer(id: String) {
        removePlayerUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    getPlayersList(teamId)
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

    internal fun updatePlayer(player: Player) {
        if(validateData(player)) updatePlayerUseCase.invoke(player).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    getPlayersList(teamId)
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

    internal fun getPlayersList(teamId: String) {
        this.teamId = teamId
        getPlayersListUseCase.invoke(teamId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    playerListMutable.value = result.data as ArrayList<Player>
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

    private fun validateData(player: Player): Boolean {
        return player.name.matches(namePattern) &&
                player.lastName.matches(lastNamePattern) &&
                player.surname.matches(surnamePattern)
    }
}