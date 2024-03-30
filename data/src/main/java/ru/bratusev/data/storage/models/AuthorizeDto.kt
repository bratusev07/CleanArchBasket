package ru.bratusev.data.storage.models

import com.google.gson.annotations.SerializedName
import ru.bratusev.data.storage.network.Common
import ru.bratusev.domain.models.AuthorizeResponse

data class AuthorizeDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_at")
    val expiresAt: Int,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    val user: User
)

fun AuthorizeDto.toResponse(): AuthorizeResponse{
    Common.userId = user.id
    Common.token = accessToken
    return AuthorizeResponse(accessToken, user.id)
}