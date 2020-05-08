package com.example.rickandmorty.serie.viewmodel.seasonstate

import com.example.rickandmorty.serie.model.season.EpisodeResult
import com.example.rickandmorty.serie.model.series.Season

sealed class SeasonState {
    data class SeasonsListSuccess(val seasons: List<Season>) : SeasonState()
    data class SeasonDetailsListSucces(val episodes: List<EpisodeResult>) : SeasonState()
}