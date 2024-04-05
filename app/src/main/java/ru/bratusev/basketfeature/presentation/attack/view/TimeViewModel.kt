package ru.bratusev.basketfeature.presentation.attack.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.usecase.CreateActionUseCase

class TimeViewModel(private val createActionUseCase: CreateActionUseCase): ViewModel() {

    internal fun createActionEntry(gameMoment: GameMoment){
        createActionUseCase.invoke(gameMoment).onEach { result ->
            when (result) {
                is Resource.Success -> {}
                is Resource.Error -> {
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}