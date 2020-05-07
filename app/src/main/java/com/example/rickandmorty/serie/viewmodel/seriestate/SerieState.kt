package com.example.rickandmorty.serie.viewmodel.seriestate

import com.example.rickandmorty.serie.model.series.Season

sealed class SerieState {
    data class SeasonsListSuccess(val seasons: List<Season>) : SerieState()
    data class SeasonsListError(val error: String) : SerieState()
}