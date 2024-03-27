package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.UserStorage
import ru.bratusev.data.storage.models.AuthorizeDto
import ru.bratusev.data.storage.models.RegistrationDto
import ru.bratusev.data.storage.models.UserModel

class NetWorkUserStorage(context: Context) : UserStorage {

    override suspend fun authorize(userModel: UserModel): AuthorizeDto {
        return Common.retrofitService.authorize("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZqdXZlcHRxcm96aG55dG5wcWZpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTAxOTcwNTgsImV4cCI6MjAyNTc3MzA1OH0.VWClwYTSKw9Ibp-79M_xOazG-lV_51Osv8VuudOxuiA1", userModel = userModel)
    }

    override fun register(userModel: UserModel): RegistrationDto {
        return Common.retrofitService.registration("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZqdXZlcHRxcm96aG55dG5wcWZpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTAxOTcwNTgsImV4cCI6MjAyNTc3MzA1OH0.VWClwYTSKw9Ibp-79M_xOazG-lV_51Osv8VuudOxuiA1", userModel = userModel)
    }

}