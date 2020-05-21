package com.example.rickandmorty.seasons.repository

import com.example.rickandmorty.Constants.API_KEY
import com.example.rickandmorty.Constants.TV_ID
import com.example.rickandmorty.seasons.model.season.Season
import com.example.rickandmorty.seasons.model.series.SerieResult
import com.example.rickandmorty.network.SerieService

class SeasonRepository {

    suspend fun getSerie(): SerieResult = SerieService.SERVICE.getSerie(TV_ID, API_KEY)

    suspend fun getSeasonDetails(seasonNumber: Long): Season{
        return SerieService.SERVICE.getSeasonDetails(TV_ID, seasonNumber, API_KEY)
    }
}