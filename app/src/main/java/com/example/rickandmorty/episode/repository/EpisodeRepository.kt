package com.example.rickandmorty.episode.repository

import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.episode.model.EpisodeResult
import com.example.rickandmorty.network.RetrofitService

class EpisodeRepository {

    suspend fun getEpisodes(page: Int): EpisodeResult =
        RetrofitService.SERVICE.getEpisodes(page)

}