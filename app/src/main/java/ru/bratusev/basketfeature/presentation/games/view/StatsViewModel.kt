package ru.bratusev.basketfeature.presentation.games.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.usecase.GetActionsUseCase

class StatsViewModel(
    private val getActionsUseCase: GetActionsUseCase
) : ViewModel() {

    private var actionListMutable = MutableLiveData<ArrayList<GameMoment>>()
    var actionListLive : LiveData<ArrayList<GameMoment>> = actionListMutable

    internal fun getActions(gameId: String) {
        getActionsUseCase.invoke(gameId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    actionListMutable.value = result.data as ArrayList<GameMoment>
                }
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }

                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}