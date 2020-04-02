package com.example.rickandmorty.character.viewmodel.characterstate

import com.example.rickandmorty.character.model.Character

sealed class CharacterState {
    data class CharactersList(val characters: List<Character>) : CharacterState()
}