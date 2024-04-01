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
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.models.TeamListResponse
import ru.bratusev.domain.usecase.CreateTeamUseCase
import ru.bratusev.domain.usecase.GetTeamsListUseCase
import ru.bratusev.domain.usecase.RemoveTeamUseCase
import ru.bratusev.domain.usecase.UpdateTeamUseCase

class TeamsViewModel(
    private val getTeamsListUseCase: GetTeamsListUseCase,
    private val removeTeamUseCase: RemoveTeamUseCase,
    private val updateTeamUseCase: UpdateTeamUseCase,
    private val createTeamUseCase: CreateTeamUseCase
) : ViewModel() {

    private val namePattern = Regex("^.*(?=.{3,18})(?=.*[а-яА-я]).*\$")

    private var teamsLiveMutable = MutableLiveData<ArrayList<TeamListResponse>>()
    var teamsLive: LiveData<ArrayList<TeamListResponse>> = teamsLiveMutable

    internal fun getTeamsList(){
        getTeamsListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success ${result.data}")
                    teamsLiveMutable.value = result.data!!
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

    internal fun removeTeam(id: String) {
        removeTeamUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    getTeamsList()
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

    internal fun updateTeam(name: String, id: String) {
        if (name.matches(namePattern))
            updateTeamUseCase.invoke(name, id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("MyNewLog", "Resource.Success")
                        getTeamsList()
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

    internal fun createTeam(team: Team) {
        if (validateData(team)) createTeamUseCase.invoke(team).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success")
                    getTeamsList()
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

    private fun validateData(team: Team): Boolean {
        return team.name.matches(namePattern)
    }
}