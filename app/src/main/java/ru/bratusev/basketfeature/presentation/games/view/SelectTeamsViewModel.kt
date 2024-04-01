package ru.bratusev.basketfeature.presentation.games.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.usecase.GetTeamsListUseCase

class SelectTeamsViewModel(private val getTeamsListUseCase: GetTeamsListUseCase) : ViewModel() {

    private val teamsMutable = MutableLiveData<ArrayList<Team>>()
    internal val teams : LiveData<ArrayList<Team>> = teamsMutable

    internal fun getTeamsList(){
        //teamsMutable.value = getTeamsListUseCase.invoke()
    }

}