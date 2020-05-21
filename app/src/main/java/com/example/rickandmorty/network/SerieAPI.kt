package com.example.rickandmorty.network

import com.example.rickandmorty.seasons.model.season.Season
import com.example.rickandmorty.seasons.model.series.SerieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieAPI {

    @GET("tv/{tv_id}")
    suspend fun getSerie(
        @Path("tv_id") id: Long,
        @Query("api_key") apiKey: String?
    ): SerieResult

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("tv_id") id: Long,
        @Path("season_number") season_number: Long,
        @Query("api_key") apiKey: String?
    ): Season

}