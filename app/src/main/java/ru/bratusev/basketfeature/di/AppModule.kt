package ru.bratusev.basketfeature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bratusev.basketfeature.presentation.signIn.view.SignInViewModel
import ru.bratusev.basketfeature.presentation.signUp.view.SignUpViewModel

val appModule = module {

    viewModel<SignInViewModel>{
        SignInViewModel(
            authorizeUseCase = get()
        )
    }

    viewModel<SignUpViewModel>{
        SignUpViewModel(
            registrationUseCase = get()
        )
    }
}