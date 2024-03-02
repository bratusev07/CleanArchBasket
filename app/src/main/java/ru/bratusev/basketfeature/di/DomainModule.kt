package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.domain.usecase.AuthorizeUseCase
import ru.bratusev.domain.usecase.CreateTeamUseCase
import ru.bratusev.domain.usecase.GetTeamsListUseCase
import ru.bratusev.domain.usecase.RegistrationUseCase
import ru.bratusev.domain.usecase.RemoveTeamUseCase
import ru.bratusev.domain.usecase.UpdateTeamUseCase

val domainModule = module {

    factory<AuthorizeUseCase> {
        AuthorizeUseCase(userRepository = get())
    }

    factory<RegistrationUseCase> {
        RegistrationUseCase(userRepository = get())
    }

    factory<GetTeamsListUseCase> {
        GetTeamsListUseCase(teamsRepository = get())
    }

    factory<RemoveTeamUseCase> {
        RemoveTeamUseCase(teamsRepository = get())
    }

    factory<UpdateTeamUseCase> {
        UpdateTeamUseCase(teamsRepository = get())
    }

    factory<CreateTeamUseCase> {
        CreateTeamUseCase(teamsRepository = get())
    }
}