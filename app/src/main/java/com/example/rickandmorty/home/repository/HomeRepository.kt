package com.example.rickandmorty.home.repository

import com.example.rickandmorty.Constants
import com.example.rickandmorty.network.SerieService
import com.example.rickandmorty.seasons.model.series.SerieResult

class HomeRepository {

    suspend fun getSerie(): SerieResult = SerieService.SERVICE.getSerie(
        Constants.TV_ID,
        Constants.API_KEY
    )
}