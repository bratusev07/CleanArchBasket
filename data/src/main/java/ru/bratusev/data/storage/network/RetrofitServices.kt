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
import ru.bratusev.data.storage.models.TeamListDto
import ru.bratusev.data.storage.models.TeamModel
import ru.bratusev.data.storage.models.UserModel

interface RetrofitServices {

    @POST("auth/v1/token?grant_type=password")
    suspend fun authorize(@Header("apikey") apikey: String, @Body userModel: UserModel): AuthorizeDto

    @POST("auth/v1/signup")
    suspend fun registration(@Header("apikey") apikey: String, @Body userModel: UserModel): RegistrationDto

    @GET("rest/v1/teams")
    suspend fun getTeams(@Header("apikey") apikey: String, @Header("Authorize") authorize: String, @Query("user_id") id: String): TeamListDto

    @POST("removeTeam")
    suspend fun removeTeam(@Query("id") id: String): Call<Void>

    @POST("updateTeam")
    suspend fun updateTeam(@Query("id") id: String, @Body name: String): Boolean

    @POST("createTeam")
    suspend fun createTeam(@Body teamModel: TeamModel): Boolean

    @GET("players")
    suspend fun getPlayers(): Boolean

    @POST("removePlayer")
    suspend fun removePlayer(@Query("id") id: String): Boolean

    @POST("updateTeam")
    suspend fun updatePlayer(@Query("id") id: String, @Body playerModel: PlayerModel): Call<Void>

    @POST("createPlayer")
    suspend fun createPlayer(@Body playerModel: PlayerModel): Call<Void>
}