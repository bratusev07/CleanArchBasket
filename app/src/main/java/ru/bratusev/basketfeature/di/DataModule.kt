package ru.bratusev.basketfeature.di

import org.koin.dsl.module
import ru.bratusev.data.repository.UserRepositoryImpl
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.sharedPrefs.SharedPrefUserStorage
import ru.bratusev.domain.repository.UserRepository


val dataModule = module {

    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }
}