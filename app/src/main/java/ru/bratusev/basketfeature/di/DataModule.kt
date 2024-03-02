package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.data.repository.TeamsRepositoryImpl
import ru.bratusev.data.repository.UserRepositoryImpl
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.network.NetWorkTeamStorage
import ru.bratusev.data.storage.network.NetWorkUserStorage
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
        NetWorkTeamStorage(context = get())
    }

    single<TeamsRepository> {
        TeamsRepositoryImpl(teamsStorage = get())
    }
}