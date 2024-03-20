package ru.bratusev.basketfeature.presentation.attack.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.domain.models.Team

class TimeViewModel(): ViewModel() {

    private val myTeamMutable = MutableLiveData<Team>()
    internal val myTeam: LiveData<Team> = myTeamMutable

    private val enemyTeamMutable = MutableLiveData<Team>()
    internal val enemyTeam: LiveData<Team> = enemyTeamMutable

    internal fun setTeamValue(team: Team, isMy: Boolean){
        if(team.name.isEmpty()) return
        if (isMy) myTeamMutable.value = team
        else enemyTeamMutable.value = team
    }
}