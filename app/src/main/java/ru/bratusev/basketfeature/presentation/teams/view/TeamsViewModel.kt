package ru.bratusev.basketfeature.presentation.teams.view

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

    private val namePattern = Regex("^.*(?=.{3,18})(?=.*[а-яА-я]).*\$")

    private var teamsLiveMutable = MutableLiveData<ArrayList<Team>>()
    var teamsLive: LiveData<ArrayList<Team>> = teamsLiveMutable

    internal fun getTeamsList() {
        teamsLiveMutable.value = getTeamsListUseCase.execute()
    }

    internal fun removeTeam(index: Int) {
        if (removeTeamUseCase.execute(index))
            teamsLiveMutable.value?.removeAt(index)
    }

    internal fun updateTeam(name: String, index: Int) {
        if (name.matches(namePattern))
            if (updateTeamUseCase.execute(name, index))
                teamsLiveMutable.value?.get(index)?.name = name
    }

    internal fun createTeam(team: Team) {
        if (validateData(team)) createTeamUseCase.execute(team)
    }

    private fun validateData(team: Team): Boolean {
        return team.name.matches(namePattern)
    }
}