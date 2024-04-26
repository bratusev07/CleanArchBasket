package ru.bratusev.basketfeature.presentation.signIn.view

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.bratusev.domain.Resource
import ru.bratusev.domain.models.AuthorizeResponse
import ru.bratusev.domain.models.UserData
import ru.bratusev.domain.usecase.AuthorizeUseCase

class SignInViewModel(
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<Boolean>()
    var resultLive: LiveData<Boolean> = resultLiveMutable

    private var hintMailLiveMutable = MutableLiveData<Boolean>()
    var hintMailLive: LiveData<Boolean> = hintMailLiveMutable

    private var hintDialogMailLiveMutable = MutableLiveData<Boolean>()
    var hintDialogMailLive: LiveData<Boolean> = hintDialogMailLiveMutable

    private var hintPassLiveMutable = MutableLiveData<Boolean>()
    var hintPassLive: LiveData<Boolean> = hintPassLiveMutable

    private val isLoadingMutable = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = isLoadingMutable

    private val isErrorMutable = MutableLiveData<Boolean>()
    var isError: LiveData<Boolean> = isErrorMutable

    private val emailPattern = Regex("^[A-Z0-9._%+-]+@[A-Z0-9-]+\\.[A-Z]{2,4}$", RegexOption.IGNORE_CASE)
    private val passPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,24}\$")

    private fun authorize(userData: UserData){
        authorizeUseCase.invoke(userData).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("MyNewLog", "Resource.Success ${(result.data as AuthorizeResponse).uuid}")
                    resultLiveMutable.value = true
                    isErrorMutable.value = false
                    isLoadingMutable.value = false
                }
                is Resource.Error -> {
                    isLoadingMutable.value = false
                    isErrorMutable.value = true
                    Log.d("MyNewLog", "Resource.Error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    isLoadingMutable.value = true
                    isErrorMutable.value = false
                    Log.d("MyNewLog", "Resource.Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    internal fun recoverPassword(mail: String){
        hintDialogMailLiveMutable.value = mail.matches(emailPattern)
    }

    internal fun validateData(userData: UserData) {
        if (validateMail(userData.mail) && validatePassword(userData.password))
                authorize(userData = userData)
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