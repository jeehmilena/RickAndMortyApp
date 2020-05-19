package com.example.rickandmorty.seasons.viewmodel.seasonstate

import com.example.rickandmorty.seasons.model.season.EpisodeResult
import com.example.rickandmorty.seasons.model.series.Season

sealed class SeasonState {
    data class SeasonsListSuccess(val seasons: List<Season>) : SeasonState()
    data class SeasonDetailsListSucces(val episodes: List<EpisodeResult>) : SeasonState()
}