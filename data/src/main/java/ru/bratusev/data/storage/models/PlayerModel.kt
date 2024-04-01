package ru.bratusev.data.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PlayerModel(
    val name: String = "Без имени",
    val number: Int = 1,
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("father_name")
    val fatherName: String = "",
    @SerialName("team_id")
    val teamId: String = "",
    val id: String = "",
    @SerialName("user_id")
    val userId: String = "",
)