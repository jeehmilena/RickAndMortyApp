package com.example.rickandmorty.home.usecase

import com.example.rickandmorty.home.repository.HomeRepository

class HomeUseCase {
    private val repository = HomeRepository()

    suspend fun getCreatorsList() = repository.getSerie()
}