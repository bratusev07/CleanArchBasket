package ru.bratusev.data.storage.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import ru.bratusev.data.storage.models.AuthorizeDto
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.RegistrationDto
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.models.UserModel

interface RetrofitServices {

    @POST("auth/v1/token?grant_type=password")
    suspend fun authorize(@Header("apikey") apikey: String, @Body userModel: UserModel): AuthorizeDto

    @POST("auth/v1/signup")
    fun registration(@Header("apikey") apikey: String, @Body userModel: UserModel): RegistrationDto

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