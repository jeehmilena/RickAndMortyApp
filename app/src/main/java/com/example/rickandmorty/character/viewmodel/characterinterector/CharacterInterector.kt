package com.example.rickandmorty.character.viewmodel.characterinterector

import com.example.rickandmorty.character.model.Character

sealed class CharacterInterector {
    data class ShowList(val page: Int) : CharacterInterector()
    data class CharacterDetail(val character: Character) : CharacterInterector()
}