package com.example.rickandmorty.serie.usecase

import com.example.rickandmorty.serie.repository.SerieRepository

class SerieUseCase {
    private val repository = SerieRepository()
    suspend fun getSerieResult() = repository.getSerie()
}