package ru.bratusev.data.storage.models

import ru.bratusev.domain.models.RegistrationResponse

data class RegistrationDto(
    val app_metadata: AppMetadata,
    val aud: String,
    val confirmation_sent_at: String,
    val created_at: String,
    val email: String,
    val id: String,
    val identities: List<Identity>,
    val is_anonymous: Boolean,
    val phone: String,
    val role: String,
    val updated_at: String,
    val user_metadata: UserMetadata
)

fun RegistrationDto.toResponse() : RegistrationResponse{
    return RegistrationResponse(id)
}