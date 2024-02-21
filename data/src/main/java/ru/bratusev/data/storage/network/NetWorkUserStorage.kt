package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.UserModel

class NetWorkUserStorage(context: Context) : UserStorage {

    override fun authorize(userModel: UserModel): Boolean {
        Common.retrofitService.authorize(userModel = userModel)
        return true
    }

    override fun register(userModel: UserModel): Boolean {
        Common.retrofitService.registration(userModel = userModel)
        return true
    }

}