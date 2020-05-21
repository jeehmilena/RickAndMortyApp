package com.example.rickandmorty.home.usecase

import com.example.rickandmorty.Constants.CREATOR
import com.example.rickandmorty.Constants.GENRES
import com.example.rickandmorty.home.model.InformationHome
import com.example.rickandmorty.home.repository.HomeRepository

class HomeUseCase {
    private val repository = HomeRepository()

    suspend fun getCreatorsList() = repository.getSerie()
}