package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.data.repository.ActionRepositoryImpl
import ru.bratusev.data.repository.GameRepositoryImpl
import ru.bratusev.data.repository.TeamRepositoryImpl
import ru.bratusev.data.repository.TeamsRepositoryImpl
import ru.bratusev.data.repository.UserRepositoryImpl
import ru.bratusev.data.storage.ActionStorage
import ru.bratusev.data.storage.GameStorage
import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.network.NetWorkActionStorage
import ru.bratusev.data.storage.network.NetWorkGameStorage
import ru.bratusev.data.storage.network.NetWorkTeamStorage
import ru.bratusev.data.storage.network.NetWorkTeamsStorage
import ru.bratusev.data.storage.network.NetWorkUserStorage
import ru.bratusev.domain.repository.ActionRepository
import ru.bratusev.domain.repository.GameRepository
import ru.bratusev.domain.repository.TeamRepository
import ru.bratusev.domain.repository.TeamsRepository
import ru.bratusev.domain.repository.UserRepository


val dataModule = module {

    single<UserStorage> {
        NetWorkUserStorage(context = get())
    }

    single<UserRepository>{
        UserRepositoryImpl(userStorage = get())
    }

    single<TeamsStorage> {
        NetWorkTeamsStorage(context = get())
    }

    single<TeamsRepository> {
        TeamsRepositoryImpl(teamsStorage = get())
    }

    single<TeamStorage> {
        NetWorkTeamStorage(context = get())
    }

    single<TeamRepository> {
        TeamRepositoryImpl(teamStorage = get())
    }

    single<GameStorage> {
        NetWorkGameStorage(context = get())
    }

    single<GameRepository> {
        GameRepositoryImpl(gameStorage = get())
    }

    single<ActionRepository> {
        ActionRepositoryImpl(actionStorage = get())
    }

    single<ActionStorage> {
        NetWorkActionStorage(context = get())
    }
}