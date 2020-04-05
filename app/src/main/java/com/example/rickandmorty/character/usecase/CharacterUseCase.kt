package com.example.rickandmorty.character.usecase

import com.example.rickandmorty.character.repository.CharacterRepository

class CharacterUseCase {
    private val repository = CharacterRepository()

    suspend fun getCharacterResult(page: Int) = repository.getCharacters(page)
}