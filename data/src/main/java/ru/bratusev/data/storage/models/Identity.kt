package ru.bratusev.data.storage.models

data class Identity(
    val created_at: String,
    val email: String,
    val id: String,
    val identity_data: IdentityData,
    val identity_id: String,
    val last_sign_in_at: String,
    val provider: String,
    val updated_at: String,
    val user_id: String
)