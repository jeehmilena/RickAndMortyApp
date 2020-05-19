package com.example.rickandmorty.seasons.viewmodel.seasoninterector

sealed class SeasonInterector {
    object ShowListSeasons : SeasonInterector()
    data class ShowListSeasonDetail(val numberSeason: Long) : SeasonInterector()
}