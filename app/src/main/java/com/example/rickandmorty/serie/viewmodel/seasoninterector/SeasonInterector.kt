package com.example.rickandmorty.serie.viewmodel.seasoninterector

sealed class SeasonInterector {
    object ShowListSeasons : SeasonInterector()
    data class ShowListSeasonDetail(val numberSeason: Long) : SeasonInterector()
}