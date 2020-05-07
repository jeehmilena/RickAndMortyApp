package com.example.rickandmorty.character.repository

import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.character.network.CharacterService

class CharacterRepository {

    suspend fun getCharacters(page: Int): CharacterResult = CharacterService.SERVICE.getCharacters(page)
}
