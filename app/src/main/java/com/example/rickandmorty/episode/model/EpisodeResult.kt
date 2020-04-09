package com.example.rickandmorty.episode.model

import com.example.rickandmorty.Info
import com.google.gson.annotations.SerializedName

data class EpisodeResult(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val episodes: List<Episode>
)