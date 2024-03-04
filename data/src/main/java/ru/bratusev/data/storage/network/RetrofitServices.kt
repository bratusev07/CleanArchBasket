package ru.bratusev.data.storage.network

import retrofit2.Call
import retrofit2.http.*
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.models.UserModel

interface RetrofitServices {

    @POST("authorize")
    fun authorize(@Body userModel: UserModel): Call<Void>

    @POST("registration")
    fun registration(@Body userModel: UserModel): Call<Void>

    @GET("teams")
    fun getTeams(): Call<Void>

    @POST("removeTeam")
    fun removeTeam(@Query("id") id: Int): Call<Void>

    @POST("updateTeam")
    fun updateTeam(@Query("id") id: Int, @Body name: String): Call<Void>

    @POST("createTeam")
    fun createTeam(@Body teamModel: TeamModel): Call<Void>

    @GET("players")
    fun getPlayers(): Call<Void>

    @POST("removePlayer")
    fun removePlayer(@Query("id") id: Int): Call<Void>

    @POST("updateTeam")
    fun updatePlayer(@Query("id") id: Int, @Body playerModel: PlayerModel): Call<Void>

    @POST("createPlayer")
    fun createPlayer(@Body playerModel: PlayerModel): Call<Void>
}