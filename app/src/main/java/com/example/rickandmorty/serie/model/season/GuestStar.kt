package com.example.rickandmorty.serie.model.season

data class GuestStar(
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val profile_path: String?
)