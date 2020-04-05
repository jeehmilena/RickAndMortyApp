package com.example.rickandmorty.character.viewmodel.characterevent

import com.example.rickandmorty.character.model.Character

sealed class CharacterEvent {
    data class Loading(val status: Boolean) : CharacterEvent()
    data class ShowDetail(val character: Character): CharacterEvent()
}