package ru.bratusev.data.storage.network

import retrofit2.Call
import retrofit2.http.*
import ru.bratusev.data.storage.models.UserModel

interface RetrofitServices {

    @POST("authorize")
    fun authorize(@Body userModel: UserModel): Call<Void>

    @POST("registration")
    fun registration(@Body userModel: UserModel): Call<Void>

    @GET("teams")
    fun getTeams(): Call<Void>

    @POST("remove")
    fun removeTeam(@Query("id") id: Int): Call<Void>

    @POST("update")
    fun updateTeam(@Query("id") id: Int, @Body name: String): Call<Void>
}