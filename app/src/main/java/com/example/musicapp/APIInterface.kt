package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {

    @Headers("X-RapidAPI-Key: 7941ab1575msh382b0ff55cfeca0p10935bjsnc5647f49de28",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<musicData>
}