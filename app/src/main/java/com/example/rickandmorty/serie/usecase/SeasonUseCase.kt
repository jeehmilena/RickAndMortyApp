package com.example.rickandmorty.serie.usecase

import com.example.rickandmorty.serie.repository.SeasonRepository

class SeasonUseCase {
    private val repository = SeasonRepository()

    suspend fun getSerieResult() = repository.getSerie()

    suspend fun getSeasonDetails(seasonNumber: Long) = repository.getSeasonDetails(seasonNumber)
}