package com.example.rickandmorty.character.viewmodel.characterinterector

import com.example.rickandmorty.character.model.Character

sealed class CharacterInterector {
    object ShowList: CharacterInterector()
    data class CharacterDetail(val result: Character): CharacterInterector()
}