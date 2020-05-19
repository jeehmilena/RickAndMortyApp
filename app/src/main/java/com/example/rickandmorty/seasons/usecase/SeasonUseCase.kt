package com.example.rickandmorty.seasons.usecase

import com.example.rickandmorty.seasons.repository.SeasonRepository

class SeasonUseCase {
    private val repository = SeasonRepository()

    suspend fun getSerieResult() = repository.getSerie()

    suspend fun getSeasonDetails(seasonNumber: Long) = repository.getSeasonDetails(seasonNumber)
}