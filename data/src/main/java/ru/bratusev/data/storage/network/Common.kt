package ru.bratusev.data.storage.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Common {
    private val BASE_URL = "https://vjuveptqrozhnytnpqfi.supabase.co/"

    internal val key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZqdXZlcHRxcm96aG55dG5wcWZpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTAxOTcwNTgsImV4cCI6MjAyNTc3MzA1OH0.VWClwYTSKw9Ibp-79M_xOazG-lV_51Osv8VuudOxuiA"
    internal var userId = ""
    internal var token = ""

    fun getSupabaseService() : SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = BASE_URL,
            supabaseKey = key,
        ){
            install(Postgrest){
                jwtToken = token
            }
        }
    }

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}