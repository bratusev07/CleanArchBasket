package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.domain.usecase.AuthorizeUseCase
import ru.bratusev.domain.usecase.RegistrationUseCase

val domainModule = module {

    factory<AuthorizeUseCase> {
        AuthorizeUseCase(userRepository = get())
    }

    factory<RegistrationUseCase> {
        RegistrationUseCase(userRepository = get())
    }
}