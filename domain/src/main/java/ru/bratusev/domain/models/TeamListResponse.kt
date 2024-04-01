package ru.bratusev.domain.models

import java.io.Serializable

class TeamListResponse(
    val id: String,
    var name: String,
    val userId: String
) : Serializable