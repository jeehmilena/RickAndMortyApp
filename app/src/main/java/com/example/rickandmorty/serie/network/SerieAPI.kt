package com.example.rickandmorty.serie.network

import com.example.rickandmorty.serie.model.series.SerieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieAPI {

    @GET("tv/{tv_id}")
    suspend fun getSerie(
        @Path("tv_id") id: Long,
        @Query("api_key") apiKey: String?
    ): SerieResult
}