package com.credopay.network


import com.credopay.homeScreen.HomeListModelItem
import retrofit2.Call
import retrofit2.http.GET

interface AppApiService {

    @GET("Characters")
    fun getAllCharacters(): Call<List<HomeListModelItem>>

}