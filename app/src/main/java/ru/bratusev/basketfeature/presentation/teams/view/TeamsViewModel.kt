package ru.bratusev.basketfeature.presentation.teams.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.domain.models.Team
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

    private var teamsLiveMutable = MutableLiveData<ArrayList<Team>>()
    var teamsLive: LiveData<ArrayList<Team>> = teamsLiveMutable

    fun getTeamsList(){
        teamsLiveMutable.value = getTeamsListUseCase.execute()
    }

    fun removeTeam(index: Int){
        if(removeTeamUseCase.execute(index))
            teamsLiveMutable.value?.removeAt(index)
    }

    fun updateTeam(name: String, index: Int){
        if(updateTeamUseCase.execute(name, index))
            teamsLiveMutable.value?.get(index)?.name = name
    }

    fun createTeam(team: Team){
        Log.d("MyLog", team.toString())
        createTeamUseCase.execute(team)
    }

}