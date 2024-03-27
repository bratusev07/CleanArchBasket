package ru.bratusev.data.storage.network

object Common {
    private val BASE_URL = "https://vjuveptqrozhnytnpqfi.supabase.co/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}