package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.AuthorizeDto
import ru.bratusev.data.storage.models.RegistrationDto
import ru.bratusev.data.storage.models.UserModel

class NetWorkUserStorage(context: Context) : UserStorage {

    override suspend fun authorize(userModel: UserModel): AuthorizeDto {
        return Common.retrofitService.authorize(
            Common.key, userModel = userModel
        )
    }

    override suspend fun register(userModel: UserModel): RegistrationDto {
        return Common.retrofitService.registration(
            Common.key, userModel = userModel
        )
    }

}