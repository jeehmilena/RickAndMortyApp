package com.example.rickandmorty.character.repository

import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.network.RetrofitService

class CharacterRepository {

    suspend fun getCharacters(): CharacterResult {
        return RetrofitService.service.getCharacters()
    }
}