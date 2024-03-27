package ru.bratusev.data.storage.models

import com.google.gson.annotations.SerializedName

class UserModel(@SerializedName("email")val mail: String, val password: String)