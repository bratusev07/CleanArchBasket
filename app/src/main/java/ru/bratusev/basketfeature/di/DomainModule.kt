package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.domain.usecase.GetUserNameUseCase
import ru.bratusev.domain.usecase.SaveUserNameUseCase

val domainModule = module {

    factory<GetUserNameUseCase> {
        GetUserNameUseCase(userRepository = get())
    }

    factory<SaveUserNameUseCase> {
        SaveUserNameUseCase(userRepository = get())
    }
}