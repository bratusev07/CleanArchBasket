package ru.bratusev.data.storage.models

data class IdentityData(
    val email: String,
    val email_verified: Boolean,
    val phone_verified: Boolean,
    val sub: String
)