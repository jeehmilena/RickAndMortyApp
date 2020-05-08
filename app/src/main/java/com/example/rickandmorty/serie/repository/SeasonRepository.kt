package com.example.rickandmorty.serie.repository

import com.example.rickandmorty.Constants.API_KEY
import com.example.rickandmorty.Constants.TV_ID
import com.example.rickandmorty.serie.model.season.Season
import com.example.rickandmorty.serie.model.series.SerieResult
import com.example.rickandmorty.serie.network.SeasonService

class SeasonRepository {

    suspend fun getSerie(): SerieResult = SeasonService.SERVICE.getSerie(TV_ID, API_KEY)

    suspend fun getSeasonDetails(seasonNumber: Long): Season{
        return SeasonService.SERVICE.getSeasonDetails(TV_ID, seasonNumber, API_KEY)
    }
}