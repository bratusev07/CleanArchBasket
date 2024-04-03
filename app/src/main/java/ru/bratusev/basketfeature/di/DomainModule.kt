package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.domain.usecase.AuthorizeUseCase
import ru.bratusev.domain.usecase.CreateGameUseCase
import ru.bratusev.domain.usecase.CreatePlayerUseCase
import ru.bratusev.domain.usecase.CreateTeamUseCase
import ru.bratusev.domain.usecase.GetGameListUseCase
import ru.bratusev.domain.usecase.GetPlayersListUseCase
import ru.bratusev.domain.usecase.GetTeamByIdUseCase
import ru.bratusev.domain.usecase.GetTeamsListUseCase
import ru.bratusev.domain.usecase.RegistrationUseCase
import ru.bratusev.domain.usecase.RemovePlayerUseCase
import ru.bratusev.domain.usecase.RemoveTeamUseCase
import ru.bratusev.domain.usecase.UpdatePlayerUseCase
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

    factory<CreatePlayerUseCase> {
        CreatePlayerUseCase(teamRepository = get())
    }

    factory<RemovePlayerUseCase> {
        RemovePlayerUseCase(teamRepository = get())
    }

    factory<UpdatePlayerUseCase> {
        UpdatePlayerUseCase(teamRepository = get())
    }

    factory<GetPlayersListUseCase> {
        GetPlayersListUseCase(teamRepository = get())
    }

    factory<CreateGameUseCase> {
        CreateGameUseCase(gameRepository = get())
    }

    factory<GetGameListUseCase> {
        GetGameListUseCase(gameRepository = get())
    }

    factory<GetTeamByIdUseCase> {
        GetTeamByIdUseCase(teamsRepository = get())
    }
}