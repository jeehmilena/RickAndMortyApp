package com.example.rickandmorty.character.model

import com.example.rickandmorty.character.Info
import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)