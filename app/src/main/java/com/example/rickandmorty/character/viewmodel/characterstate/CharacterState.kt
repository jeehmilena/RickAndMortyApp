package com.example.rickandmorty.character.viewmodel.characterstate

import com.example.rickandmorty.character.model.Character

sealed class CharacterState {
    data class CharactersListSuccess(val characters: List<Character>) : CharacterState()
    data class CharactersListError(val error: String) : CharacterState()
}