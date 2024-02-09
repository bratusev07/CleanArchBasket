package ru.bratusev.domain.repository

import ru.bratusev.domain.models.SaveUserNameParam
import ru.bratusev.domain.models.UserName

interface UserRepository {

    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName
}