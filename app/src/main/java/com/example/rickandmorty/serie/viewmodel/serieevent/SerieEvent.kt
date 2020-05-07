package com.example.rickandmorty.serie.viewmodel.serieevent

sealed class SerieEvent {
    data class Loading(val status: Boolean) : SerieEvent()
}