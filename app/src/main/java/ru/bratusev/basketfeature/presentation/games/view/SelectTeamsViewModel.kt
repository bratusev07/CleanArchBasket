package ru.bratusev.basketfeature.presentation.games.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.TeamListResponse
import ru.bratusev.domain.usecase.GetTeamsListUseCase

class SelectTeamsViewModel(private val getTeamsListUseCase: GetTeamsListUseCase) : ViewModel() {

    private val teamsMutable = MutableLiveData<ArrayList<TeamListResponse>>()
    internal val teams : LiveData<ArrayList<TeamListResponse>> = teamsMutable

    internal fun getTeamsList(){
        getTeamsListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    teamsMutable.value = result.data as ArrayList<TeamListResponse>
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

}