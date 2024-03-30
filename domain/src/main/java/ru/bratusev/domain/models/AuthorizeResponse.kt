package ru.bratusev.domain.models

import java.io.Serializable

data class AuthorizeResponse(val token: String, val uuid: String) : Serializable