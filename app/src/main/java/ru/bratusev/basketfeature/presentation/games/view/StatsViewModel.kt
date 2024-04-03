package ru.bratusev.basketfeature.presentation.games.view

import androidx.lifecycle.ViewModel
import ru.bratusev.domain.usecase.GetActionsUseCase

class StatsViewModel(
    private val getActionsUseCase: GetActionsUseCase
) : ViewModel() {
}