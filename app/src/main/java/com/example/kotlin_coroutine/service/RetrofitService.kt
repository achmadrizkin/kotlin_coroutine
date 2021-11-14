package com.example.kotlin_coroutine.service

import com.example.kotlin_coroutine.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    // https://api.github.com/search/repositories?q=kotlin
    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String): RecyclerList

}