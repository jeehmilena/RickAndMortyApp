package com.example.rickandmorty.network

import com.example.rickandmorty.character.model.CharacterResult
import retrofit2.http.GET

interface Api {

    @GET("character")
    suspend fun getCharacters() : CharacterResult
}