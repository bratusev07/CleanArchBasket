package ru.bratusev.basketfeature.presentation.signUp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.RegistrationResponse
import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.usecase.RegistrationUseCase

class SignUpViewModel(
    private val registrationUseCase: RegistrationUseCase
    ) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<Boolean>()
    var resultLive: LiveData<Boolean> = resultLiveMutable

    private var hintMailLiveMutable = MutableLiveData<Boolean>()
    var hintMailLive: LiveData<Boolean> = hintMailLiveMutable

    private var hintDialogMailLiveMutable = MutableLiveData<Boolean>()
    var hintDialogMailLive: LiveData<Boolean> = hintDialogMailLiveMutable

    private var hintPassLiveMutable = MutableLiveData<Boolean>()
    var hintPassLive: LiveData<Boolean> = hintPassLiveMutable

    private val emailPattern = Regex("^[A-Z0-9._%+-]+@[A-Z0-9-]+\\.[A-Z]{2,4}$", RegexOption.IGNORE_CASE)
    private val passPattern = Regex("^.*(?=.{8,24})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#\$%&? \"]).*\$")


    private fun registration(userData: UserData) {
        registrationUseCase.invoke(userData).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success ${(result.data as RegistrationResponse).uuid}")
                    resultLiveMutable.value = true
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

    internal fun validateData(userData: UserData) {
        if (validateMail(userData.mail) && validatePassword(userData.password))
            registration(userData = userData)
    }

    private fun validateMail(mail: String): Boolean {
        val result = mail.matches(emailPattern)
        hintMailLiveMutable.value = result
        return result
    }

    private fun validatePassword(password: String): Boolean {
        val result = password.matches(passPattern)
        hintPassLiveMutable.value = result
        return result
    }
}