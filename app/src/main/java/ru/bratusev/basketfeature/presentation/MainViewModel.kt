package ru.bratusev.basketfeature.presentation

import androidx.lifecycle.ViewModel
import ru.bratusev.domain.usecase.GetUserNameUseCase
import ru.bratusev.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {


}