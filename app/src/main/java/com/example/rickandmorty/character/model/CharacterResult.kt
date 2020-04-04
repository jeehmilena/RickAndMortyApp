package com.example.rickandmorty.character.model

import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)