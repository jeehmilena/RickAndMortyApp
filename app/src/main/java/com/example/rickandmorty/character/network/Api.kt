package com.example.rickandmorty.character.network

import com.example.rickandmorty.character.model.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResult
}