package com.example.rickandmorty.serie.model.season

data class Season(
    val _id: String,
    val air_date: String,
    val episodes: List<EpisodeResult>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)