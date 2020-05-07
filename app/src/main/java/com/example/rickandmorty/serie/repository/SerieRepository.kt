package com.example.rickandmorty.serie.repository

import com.example.rickandmorty.serie.model.series.SerieResult
import com.example.rickandmorty.serie.network.SerieService

class SerieRepository {

    suspend fun getSerie(): SerieResult = SerieService.SERVICE.getSerie(60625, "9e388e7de5c0c42386ebad1002886539")
}