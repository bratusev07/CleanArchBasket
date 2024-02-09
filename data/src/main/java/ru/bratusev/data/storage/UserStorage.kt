package ru.bratusev.data.storage

import ru.bratusev.data.storage.models.User

interface UserStorage {

    fun get(): User

    fun save(user: User): Boolean
}