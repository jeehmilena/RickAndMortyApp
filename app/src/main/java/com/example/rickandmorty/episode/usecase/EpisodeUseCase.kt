package com.example.rickandmorty.episode.usecase

import com.example.rickandmorty.character.repository.CharacterRepository
import com.example.rickandmorty.episode.repository.EpisodeRepository

class EpisodeUseCase {
    private val repository = EpisodeRepository()

    suspend fun getEpisodeResult(page: Int) = repository.getEpisodes(page)
}