package com.example.rickandmorty.serie.viewmodel.seasonevent

sealed class SeasonEvent {
    data class Loading(val status: Boolean) : SeasonEvent()
}