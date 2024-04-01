package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TeamModel(var name: String, @SerialName("user_id") var userId: String = "")