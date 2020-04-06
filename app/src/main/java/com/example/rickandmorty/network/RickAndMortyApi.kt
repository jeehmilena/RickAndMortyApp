package com.example.rickandmorty.network

import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.episode.model.EpisodeResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResult

    @GET("episode/")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): EpisodeResult
}