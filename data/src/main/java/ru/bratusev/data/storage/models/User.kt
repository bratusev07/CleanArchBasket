package ru.bratusev.data.storage.models

data class User(
    val app_metadata: AppMetadata,
    val aud: String,
    val confirmed_at: String,
    val created_at: String,
    val email: String,
    val email_confirmed_at: String,
    val id: String,
    val identities: List<Identity>,
    val is_anonymous: Boolean,
    val last_sign_in_at: String,
    val phone: String,
    val role: String,
    val updated_at: String,
    val user_metadata: UserMetadata
)